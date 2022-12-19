package ast;

public class ASTDeref implements ASTNode {

    ASTNode val;

    public ASTDeref(ASTNode val) {
        this.val = val;
    }

    public IValue eval(Environment<IValue> e) {
        IValue v1 = val.eval(e);

        if (v1 instanceof VCell) {
            return ((VCell) v1).getVal();
        }

        throw new RuntimeException("illegal arguments to ! operator");

    }

    @Override
    public void compile(CodeBlock code, Environment<Coordinate> e) {
        val.compile(code, e);
        code.emit("");
    }
}
