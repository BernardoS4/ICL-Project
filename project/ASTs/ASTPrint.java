package ASTs;

public class ASTPrint implements ASTNode {

    ASTNode val;

    public IValue eval(Environment<IValue> e) {
        IValue v = val.eval(e);
        IValue result = null;

        if (v instanceof VBool) 
            result = new VBool(((VBool) v).getVal());
        else
            result = new VInt(((VInt) v).getVal());
        return result;
    }

    public ASTPrint(ASTNode val) {
        this.val = val;
    }

    @Override
    public void compile(CodeBlock code, Environment<Coordinate> e) {
        val.compile(code, e);
        code.emit("");
    }
}
