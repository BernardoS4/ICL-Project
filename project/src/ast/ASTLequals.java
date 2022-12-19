package ast;

import Types.IType;
import Types.TypeBool;

import static Utils.Utils.argumentError;
import static Utils.Utils.typeError;

public class ASTLequals implements ASTNode {

    ASTNode lhs, rhs;

    public IValue eval(Environment<IValue> e) {
        IValue v1 = lhs.eval(e);

        if (v1 instanceof VInt) {
            IValue v2 = rhs.eval(e);
            if (v2 instanceof VInt) {
                return new VBool(((VInt) v1).getVal() <= ((VInt) v2).getVal());
            }
        }
        throw new RuntimeException(argumentError("<="));
    }

    public ASTLequals(ASTNode l, ASTNode r) {
        lhs = l;
        rhs = r;
    }

    @Override
    public void compile(CodeBlock code, Environment<Coordinate> e) {
        lhs.compile(code, e);
        rhs.compile(code, e);
        code.emit("isub");
        code.emit("ifle L1");
        code.emit("sipush 0");
        code.emit("goto L2");
        code.emit("L1: sipush 1");
        code.emit("L2:");
    }

    @Override
    public IType typecheck(Environment<IType> e) {
        IType v1 = lhs.typecheck(e);
        if (v1 instanceof TypeBool) {
            IType v2 = rhs.typecheck(e);
            if (v2 instanceof TypeBool)
                return v1;
        }
        throw new RuntimeException(typeError("<="));
    }
}
