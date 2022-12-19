package ast;

import Types.IType;

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
        typecheck(new Environment<IType>(null, 0));
        lhs.compile(code, e);
        rhs.compile(code, e);
    }

    @Override
    public IType typecheck(Environment<IType> e) {
        lhs.typecheck(e);
        IType v2 = rhs.typecheck(e);
        return v2;
    }
}
