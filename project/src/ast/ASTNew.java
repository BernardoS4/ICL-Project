package ast;

public class ASTNew implements ASTNode {

    private ASTNode val;

    public ASTNew(ASTNode val) {
        this.val = val;
    }

    public IValue eval(Environment<IValue> e) {
        IValue v = val.eval(e);
        return new VCell(v);
    }

    @Override
    public void compile(CodeBlock code, Environment<Coordinate> e) {
        val.compile(code, e);
        code.emit("");
    }
}
