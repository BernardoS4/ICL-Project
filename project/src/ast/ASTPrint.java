package ast;

public class ASTPrint implements ASTNode {

    ASTNode val;

    public ASTPrint(ASTNode val) {
        this.val = val;
    }

    public IValue eval(Environment<IValue> e) {
        IValue v1 = val.eval(e);
        return v1;
    }

    @Override
    public void compile(CodeBlock code, Environment<Coordinate> e) {
        val.compile(code, e);
    }

    @Override
    public IType typecheck(Environment<IType> e) {
        return val.typecheck(e);
    }
}
