package ast;

public class ASTLower implements ASTNode {

    ASTNode lhs, rhs;

    public IValue eval(Environment<IValue> e) {
        IValue v1 = lhs.eval(e);
        IValue v2;

        if (v1 instanceof VInt) {
            v2 = rhs.eval(e);
            if (v2 instanceof VInt) {
                return new VBool(((VInt) v1).getVal() < ((VInt) v2).getVal());
            }
        } else if (v1 instanceof VCell) {
            v2 = rhs.eval(e);
            v1 = ((VCell) v1).getVal();
            if (v1 instanceof VInt && v2 instanceof VInt) {
                return new VBool(((VInt) v1).getVal() < ((VInt) v2).getVal());
            }
        }
        throw new RuntimeException("illegal arguments to < operator");
    }

    public ASTLower(ASTNode l, ASTNode r) {
        lhs = l;
        rhs = r;
    }

    @Override
    public void compile(CodeBlock code, Environment<Coordinate> e) {
        lhs.compile(code, e);
        rhs.compile(code, e);
        code.emit("isub");
        code.emit("iflt L1");
        code.emit("sipush 0");
        code.emit("goto L2");
        code.emit("L1: sipush 1");
        code.emit("L2:");
    }
}