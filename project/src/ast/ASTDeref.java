package ast;

public class ASTDeref implements ASTNode {

    ASTNode val;

    public IValue eval(Environment<IValue> e) {
        IValue v1 = val.eval(e);

        if (v1 instanceof VBool)
            return new VBool(((VBool) v1).getVal());
        else if (v1 instanceof VInt)
            return new VInt(((VInt) v1).getVal());
        else
            return new VCell(((VCell) v1).getVal());
    }

    public ASTDeref(ASTNode val) {
        this.val = val;
    }

    @Override
    public void compile(CodeBlock code, Environment<Coordinate> e) {
        val.compile(code, e);
        code.emit("");
    }
}
