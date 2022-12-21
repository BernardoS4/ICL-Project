package ast;

import static ast.Utils.argumentError;
import static ast.Utils.typeError;

public class ASTLower implements ASTNode {

    ASTNode lhs, rhs;

    public IValue eval(Environment<IValue> e) {
        IValue v1 = lhs.eval(e);

        if (v1 instanceof VInt) {
            IValue v2 = rhs.eval(e);
            if (v2 instanceof VInt) {
                return new VBool(((VInt) v1).getVal() < ((VInt) v2).getVal());
            }
        }
        throw new RuntimeException(argumentError("<"));
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
        code.emit("iflt L13");
        code.emit("sipush 0");
        code.emit("goto L14");
        code.emit("L13:");
        code.emit("sipush 1");
        code.emit("L14:");
    }

    @Override
    public IType typecheck(Environment<IType> e) {
        IType v1 = lhs.typecheck(e);
        if (v1 instanceof TypeInt) {
            IType v2 = rhs.typecheck(e);
            if (v2 instanceof TypeInt)
                return new TypeBool(((TypeInt) v1).getVal() < ((TypeInt) v2).getVal());
        }
        throw new RuntimeException(typeError("<"));
    }
}