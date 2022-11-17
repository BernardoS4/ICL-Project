package ASTs;

public class ASTAssign implements ASTNode {

    ASTNode lhs, rhs;

    public ASTAssign(ASTNode l, ASTNode r) {
        lhs = l;
        rhs = r;
    }

    @Override
    public IValue eval(Environment<IValue> e) {
        IValue v1 = lhs.eval(e);
        if (v1 instanceof VCell) {
            IValue v2 = rhs.eval(e);
            ((VCell) v1).set(v2);
            return v2;
        }
        throw new RuntimeException("illegal arguments to := operator");
    }

    @Override
    public void compile(CodeBlock code, Environment<Coordinate> e) {
        lhs.compile(code, e);
        rhs.compile(code, e);
    }
}
