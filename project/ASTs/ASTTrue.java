package ASTs;

public class ASTTrue implements ASTNode {

    private VBool val;

    public ASTTrue(VBool n) {
            val = n;
    }

    public VBool eval(Environment<IValue> e) {
            return val;
    }

    @Override
    public void compile(CodeBlock code, Environment<Coordinate> e) {
            code.emit("sipush" + val);
    }

}
