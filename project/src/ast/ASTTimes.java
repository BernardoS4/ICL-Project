package ast;

import static ast.Utils.argumentError;
import static ast.Utils.typeError;

public class ASTTimes implements ASTNode {

    ASTNode lhs, rhs;

    public ASTTimes(ASTNode l, ASTNode r) {
        lhs = l;
        rhs = r;
    }

    public IValue eval(Environment<IValue> e) {
        IValue v1 = lhs.eval(e);
        if (v1 instanceof VInt) {
            IValue v2 = rhs.eval(e);
            if (v2 instanceof VInt) {
                return new VInt(((VInt) v1).getVal() * ((VInt) v2).getVal());
            }
        }
        throw new RuntimeException(argumentError("*"));
    }

    @Override
    public void compile(CodeBlock code, Environment<Coordinate> e) {
        typecheck(new Environment<IType>(null, 0));
        lhs.compile(code, e);
        rhs.compile(code, e);
        code.emit("imul");
    }

    @Override
    public IType typecheck(Environment<IType> e) {
        IType v1 = lhs.typecheck(e);
        if (v1 instanceof TypeInt) {
            IType v2 = rhs.typecheck(e);
            if (v2 instanceof TypeInt)
                return v1;
        }
        throw new RuntimeException(typeError("*"));
    }
}
