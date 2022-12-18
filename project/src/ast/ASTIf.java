package ast;

public class ASTIf implements ASTNode {

    ASTNode cond, lhs, rhs;

    public ASTIf(ASTNode cond, ASTNode lhs, ASTNode rhs) {
        this.cond = cond;
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public IValue eval(Environment<IValue> e) {
        IValue v1 = cond.eval(e);
        IValue v2;

        if (v1 instanceof VBool) {
            if (((VBool) v1).getVal()) {
                v2 = lhs.eval(e);
            } else
                v2 = rhs.eval(e);
            return v2;
        }
        throw new RuntimeException("illegal arguments to if operation");
    }

    @Override
    public void compile(CodeBlock code, Environment<Coordinate> e) {
    }
}
