package ast;

import static ast.Utils.ALOAD_3;
import static ast.Utils.FIELD_PREFIX;
import static ast.Utils.FRAME_PREFIX;

import java.util.Map;
import java.util.Map.Entry;

public class ASTDef implements ASTNode {

    private ASTNode body;
    private Map<String, ASTNode> vars;
    private Environment<IType> typeEnv;

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
        typeEnv = new Environment<IType>(null, 0);
        typecheck(typeEnv);
        env = env.beginScope();
        int currentLevel = env.depth();
        String frame = c.gensym(FRAME_PREFIX, currentLevel);

        c.emit("new " + frame);
        c.emit("dup");
        c.emit("invokespecial " + frame + "/<init>()V");
        c.emit("dup");
        c.emit("aload_3");

        String old_frame = "";
        if (currentLevel == 0)
            old_frame = "java/lang/Object";
        else
            old_frame = c.gensym(FRAME_PREFIX, currentLevel - 1);

        c.emit("putfield " + frame + "/sl L" + old_frame + ";");
        c.emit("astore_3");

        String field;
        int counter = 0;
        IType type;
        String sType = "";
        String variables = "";
        for (Entry<String, ASTNode> exp : vars.entrySet()) {
            c.emit(ALOAD_3);
            exp.getValue().compile(c, env);

            type = typeEnv.find(exp.getKey());
            if (type instanceof TypeInt)
                sType = "I";
            else if (type instanceof TypeBool)
                sType = "Z";
            else {
                sType = "L";
                while (type instanceof TypeRef) {
                    sType += "ref_of_";
                    type = ((TypeRef) type).getVal();
                }
                if (type instanceof TypeInt) {
                    sType += "int";
                } else {
                    sType += "bool";
                }
                sType += ";";
            }

            variables += ".field public v" + counter + " " + sType + "\n";
            field = c.gensym(FIELD_PREFIX, counter);
            c.emit(Utils.putFrameVal(frame, field, sType));
            env.assoc(exp.getKey(), new Coordinate(env.depth(), field));
            counter++;
        }
        Utils.defFrameFile(frame, variables);
        body.compile(c, env);
        env.endScope();
    }

    @Override
    public IType typecheck(Environment<IType> e) {
        typeEnv = e.beginScope();
        IType v;
        for (Entry<String, ASTNode> exp : vars.entrySet()) {
            v = exp.getValue().typecheck(typeEnv);
            typeEnv.assoc(exp.getKey(), v);
        }
        v = body.typecheck(typeEnv);
        typeEnv.endScope();
        return v;
    }
}
