package ast;

import static ast.Utils.ALOAD_3;
import static ast.Utils.ASTORE_3;
import static ast.Utils.FIELD_PREFIX;
import static ast.Utils.FRAME_PREFIX;

import java.util.Map;
import java.util.Map.Entry;

public class ASTDef implements ASTNode {

    private ASTNode body;
    private Map<String, ASTNode> vars;

    public ASTDef(Map<String, ASTNode> m, ASTNode body) {
        this.body = body;
        this.vars = m;
    }

    public IValue eval(Environment<IValue> e) {

        e = e.beginScope();
        IValue v;
        for (Entry<String, ASTNode> exp : vars.entrySet()) {
            v = exp.getValue().eval(e);
            e.assoc(exp.getKey(), v);
        }
        v = body.eval(e);
        e.endScope();
        return v;
    }

    public void compile(CodeBlock c, Environment<Coordinate> env) {
        typecheck(new Environment<IType>(null, 0));
        // def x1 = E1 … xn = En in Body end
        env = env.beginScope();
        int currentLevel = env.depth();
        String frame = c.gensym(FRAME_PREFIX, currentLevel);
        String old_frame = c.gensym(FRAME_PREFIX, currentLevel - 1);
        String field;
        int counter = 0;

        for (Entry<String, ASTNode> exp : vars.entrySet()) {
            c.emit(ALOAD_3);
            exp.getValue().compile(c, env);
            field = c.gensym(FIELD_PREFIX, counter);
            c.emit(Utils.putFrameVal(frame, field));
            env.assoc(exp.getKey(), new Coordinate(env.depth(), field));
            // counter++;
        }

        body.compile(c, env);
        // c.emit(ALOAD_3);
        // c.emit(Utils.changeFrames(frame, old_frame));
        // c.emit(ASTORE_3);

        env.endScope();
    }

    @Override
    public IType typecheck(Environment<IType> e) {
        // TODO Auto-generated method stub
        return null;
    }
}
