package ast;

import Types.IType;
import Types.TypeInt;

import static Utils.Utils.argumentError;
import static Utils.Utils.typeError;

public class ASTSub implements ASTNode {

    ASTNode lhs, rhs;

    public IValue eval(Environment<IValue> e) {
        IValue v1 = lhs.eval(e);
        if (v1 instanceof VInt) {
            IValue v2 = rhs.eval(e);
            if (v2 instanceof VInt) {
                return new VInt(((VInt) v1).getVal() - ((VInt) v2).getVal());
            }
        }
        throw new RuntimeException(argumentError("-"));
    }

    public ASTSub(ASTNode l, ASTNode r) {
        lhs = l;
        rhs = r;
    }

    @Override
    public void compile(CodeBlock code, Environment<Coordinate> e) {

        lhs.compile(code, e);
        rhs.compile(code, e);
        code.emit("isub");
    }

    @Override
    public IType typecheck(Environment<IType> e) {
        IType v1 = lhs.typecheck(e);
        if (v1 instanceof TypeInt) {
            IType v2 = rhs.typecheck(e);
            if (v2 instanceof TypeInt)
                return v1;
        }
        throw new RuntimeException(typeError("-"));
    }
}
