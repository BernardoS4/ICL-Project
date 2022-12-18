package ast;

public class ASTNot implements ASTNode {

    ASTNode val;

    public IValue eval(Environment<IValue> e) {
        IValue v1 = val.eval(e);

        if (v1 instanceof VBool) {
            return new VBool(!((VBool) v1).getVal());
        }
        throw new RuntimeException("illegal arguments to / operator");
    }

    public ASTNot(ASTNode val) {
        this.val = val;
    }

    @Override
    public void compile(CodeBlock code, Environment<Coordinate> e) {
        val.compile(code, e);
        code.emit("");
    }
}
