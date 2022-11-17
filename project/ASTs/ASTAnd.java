package ASTs;

public class ASTAnd implements ASTNode {

    ASTNode lhs, rhs;

    public IValue eval(Environment<IValue> e) {
        IValue v1 = lhs.eval(e);

        if (v1 instanceof VBool) {
            IValue v2 = rhs.eval(e);
            if (v2 instanceof VBool) {
                return new VBool(((VBool) v1).getVal() && ((VBool) v2).getVal());
            }
        }
        throw new RuntimeException("illegal arguments to && operator");
    }

    public ASTAnd(ASTNode l, ASTNode r) {
        lhs = l;
        rhs = r;
    }

    @Override
    public void compile(CodeBlock code, Environment<Coordinate> e) {
        lhs.compile(code, e);
        rhs.compile(code, e);
        code.emit("");
    }
}
