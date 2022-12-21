package ast;

public class ASTSeq implements ASTNode {

    ASTNode lhs, rhs;

    public IValue eval(Environment<IValue> e) {
        lhs.eval(e);
        IValue v2 = rhs.eval(e);
        return v2;
    }

    public ASTSeq(ASTNode l, ASTNode r) {
        lhs = l;
        rhs = r;
    }

    @Override
    public void compile(CodeBlock code, Environment<Coordinate> e) {
        lhs.compile(code, e);
        code.emit("pop");
        rhs.compile(code, e);
    }

    @Override
    public IType typecheck(Environment<IType> e) {
        lhs.typecheck(e);
        return rhs.typecheck(e);
    }
}
