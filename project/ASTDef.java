import java.util.Map;
import java.util.Map.Entry;

public class ASTDef {

    private ASTNode body;
    private Map<String, ASTNode> vars;

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
}
