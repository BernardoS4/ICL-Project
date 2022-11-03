package ASTs;

public class ASTTimes implements ASTNode {

    ASTNode lhs, rhs;

    public int eval(Environment e) {
        int v1 = lhs.eval(e);
        int v2 = rhs.eval(e);
        return v1 * v2;
    }

    public ASTTimes(ASTNode l, ASTNode r) {
        lhs = l;
        rhs = r;
    }

    @Override
    public void compile(CodeBlock code, Environment e) {
        lhs.compile(code, e);
        rhs.compile(code, e);
        code.emit("imul");
    }
}
