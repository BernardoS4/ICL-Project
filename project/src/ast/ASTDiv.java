package ast;

import static ast.Utils.argumentError;
import static ast.Utils.typeError;

public class ASTDiv implements ASTNode {

    ASTNode lhs, rhs;

    public IValue eval(Environment<IValue> e) {
        IValue v1 = lhs.eval(e);
        if (v1 instanceof VInt) {
            IValue v2 = rhs.eval(e);
            if (v2 instanceof VInt) {
                return new VInt(((VInt) v1).getVal() / ((VInt) v2).getVal());
            }
        }
        throw new RuntimeException(argumentError("/"));
    }

    public ASTDiv(ASTNode l, ASTNode r) {
        lhs = l;
        rhs = r;
    }

    @Override
    public void compile(CodeBlock code, Environment<Coordinate> e) {
        lhs.compile(code, e);
        rhs.compile(code, e);
        code.emit("idiv");
    }

    @Override
    public IType typecheck(Environment<IType> e) {
        IType v1 = lhs.typecheck(e);
        if (v1 instanceof TypeInt) {
            IType v2 = rhs.typecheck(e);
            if (v2 instanceof TypeInt)
                return new TypeInt(10);
        }
        throw new RuntimeException(typeError("/"));
    }
}
