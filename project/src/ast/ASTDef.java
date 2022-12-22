package ast;

import static ast.Utils.ALOAD_3;
import static ast.Utils.FIELD_PREFIX;
import static ast.Utils.FRAME_PREFIX;

import java.util.ArrayList;
import java.util.List;

public class ASTDef implements ASTNode {

    private ASTNode body;
    // private Map<String, ASTNode> vars;
    private Environment<IType> typeEnv;
    List<String> sTypes;
    List<Entry> vars;
    Entry entry;

    public ASTDef(List<Entry> l, ASTNode body) {
        this.body = body;
        this.vars = l;
        this.sTypes = new ArrayList<>();
    }

    public IValue eval(Environment<IValue> e) {

        e = e.beginScope();
        IValue v;
        for (Entry exp : vars) {
            v = exp.getValue().eval(e);
            e.assoc(exp.getKey(), v);
        }
        v = body.eval(e);
        e.endScope();
        return v;
    }

    public void compile(CodeBlock c, Environment<Coordinate> env) {
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
            old_frame = "java/lang/Object;";
        else
            old_frame = c.gensym(FRAME_PREFIX, currentLevel - 1) + ";";

        c.emit("putfield " + frame + "/sl L" + old_frame);
        c.emit("astore_3");

        String field;
        int counter = 0;
        String sType = "";
        String variables = "";

        for (Entry exp : vars) {
            c.emit(ALOAD_3);
            exp.getValue().compile(c, env);
            sType = sTypes.get(counter);
            variables += ".field public v" + counter + " " + sType + "\n";
            field = c.gensym(FIELD_PREFIX, counter);
            c.emit(Utils.putFrameVal(frame, field, sType));
            env.assoc(exp.getKey(), new Coordinate(env.depth(), field));
            counter++;
        }
        Utils.defFrameFile(frame, old_frame, variables);
        body.compile(c, env);
        c.emit(ALOAD_3);
        c.emit("getfield " + frame + "/sl L" + old_frame);
        c.emit("astore_3");
        env.endScope();
    }

    @Override
    public IType typecheck(Environment<IType> e) {
        typeEnv = e.beginScope();
        IType v;
        String sType = "";
        for (Entry exp : vars) {
            v = exp.getValue().typecheck(typeEnv);
            typeEnv.assoc(exp.getKey(), v);

            if (v instanceof TypeInt)
                sType = "I";
            else if (v instanceof TypeBool)
                sType = "Z";
            else {
                sType = "L";
                while (v instanceof TypeRef) {
                    sType += "ref_of_";
                    v = ((TypeRef) v).getVal();
                }
                if (v instanceof TypeInt) {
                    sType += "int";
                } else {
                    sType += "bool";
                }
                sType += ";";
            }
            sTypes.add(sType);
        }
        v = body.typecheck(typeEnv);
        typeEnv.endScope();
        return v;
    }
}
