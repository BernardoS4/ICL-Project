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
        // falta o type
        code.emit("new ref_of_type");
        code.emit("dup");
        // falta type
        code.emit("invokespecial ref_of_" + "type" + "/<init>()V");
        code.emit("dup");
        val.compile(code, e);
        // falta type e typeJ
        code.emit("putfield ref_of_" + "type" + "/ v " + "typeJ");
    }
}
