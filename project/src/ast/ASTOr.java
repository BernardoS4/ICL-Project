package ast;

import Types.IType;

import static Utils.Utils.argumentError;
import static Utils.Utils.typeError;

public class ASTOr implements ASTNode {

    ASTNode lhs, rhs;

    public IValue eval(Environment<IValue> e) {
        IValue v1 = lhs.eval(e);

        if (v1 instanceof VBool) {
            IValue v2 = rhs.eval(e);
            if (v2 instanceof VBool) {
                return new VBool(((VBool) v1).getVal() || ((VBool) v2).getVal());
            }
        }
        throw new RuntimeException(argumentError("||"));
    }

    public ASTOr(ASTNode l, ASTNode r) {
        lhs = l;
        rhs = r;
    }

    @Override
    public void compile(CodeBlock code, Environment<Coordinate> e) {
        lhs.compile(code, e);
        rhs.compile(code, e);
        code.emit("ior");
    }

    @Override
    public IType typecheck(Environment<IType> e) {
        IType v1 = lhs.typecheck(e);
        IType v2 = rhs.typecheck(e);
        if (v1.equals(v2)) {
            return v1;
        }
        throw new RuntimeException(typeError("||"));
    }
}