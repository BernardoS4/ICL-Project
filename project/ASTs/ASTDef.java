package ASTs;

import java.util.Map;
import java.util.Map.Entry;

public class ASTDef {

    private ASTNode body;
    private Map<String, ASTNode> vars;
    private static final String FRAME_PREFIX = "frame_";
    private static final String FIELD_PREFIX = "v";

    public ASTDef(ASTNode body, Map<String, ASTNode> vars) {
        this.body = body;
        this.vars = vars;
    }

    public int eval(Environment e) {

        e = e.beginScope();
        int v;
        for (Entry<String, ASTNode> exp : vars.entrySet()) {
            v = exp.getValue().eval(e);
            e.assoc(exp.getKey(), v);
        }
        v = body.eval(e);
        e.endScope();
        return v;

    }

    public void compile(CodeBlock c, Environment env) {
        // def x1 = E1 … xn = En in Body end
        env = env.beginScope();
        String frame = c.gensym(FRAME_PREFIX);
        String field;
        // generate code for frame init and link into RT env
        for (Entry<String, ASTNode> exp : vars.entrySet()) {
            c.emit("aload 3");
            exp.getValue().compile(c, env);
            field = c.gensym(FIELD_PREFIX);
            c.emit("putfield " + frame + "/" + field);
            env.assoc(exp.getKey(), new Coordinate(env.depth(), field));
        }
        body.compile(c, env);
        // generate code for frame pop off
        c.emit("aload 3");
        c.emit("getfield " + frame + "/sl" + old_frame);
        c.emit("astore 3");
        env.endScope();
    }
}
