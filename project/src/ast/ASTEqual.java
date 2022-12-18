package ast;

public class ASTEqual implements ASTNode {

    ASTNode lhs, rhs;

    public IValue eval(Environment<IValue> e) {
        IValue v1 = lhs.eval(e);
        IValue v2 = rhs.eval(e);

        if (v1 instanceof VBool && v2 instanceof VBool)
            return new VBool(((VBool) v1).getVal() == ((VBool) v2).getVal());

        else if (v1 instanceof VInt && v2 instanceof VInt)
            return new VBool(((VInt) v1).getVal() == ((VInt) v2).getVal());

        throw new RuntimeException("illegal arguments to == operator");
    }

    public ASTEqual(ASTNode l, ASTNode r) {
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
