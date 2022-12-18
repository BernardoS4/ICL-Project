package ast;

public class ASTDeref implements ASTNode {

    ASTNode val;

    public IValue eval(Environment<IValue> e) {
        IValue v1 = val.eval(e);
        System.out.println(v1.show());
        return v1;
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
