package ast;

import static ast.Utils.argumentError;
import static ast.Utils.typeError;

public class ASTEqual implements ASTNode {

    ASTNode lhs, rhs;

    public ASTEqual(ASTNode l, ASTNode r) {
        lhs = l;
        rhs = r;
    }

    public IValue eval(Environment<IValue> e) {
        IValue v1 = lhs.eval(e);
        IValue v2 = rhs.eval(e);

        if (v1 instanceof VBool && v2 instanceof VBool)
            return new VBool(((VBool) v1).getVal() == ((VBool) v2).getVal());

        else if (v1 instanceof VInt && v2 instanceof VInt)
            return new VBool(((VInt) v1).getVal() == ((VInt) v2).getVal());

        throw new RuntimeException(argumentError("=="));
    }

    @Override
    public void compile(CodeBlock code, Environment<Coordinate> e) {
        lhs.compile(code, e);
        rhs.compile(code, e);
        code.emit("isub");
        code.emit("ifeq L3");
        code.emit("sipush 0");
        code.emit("goto L4");
        code.emit("L3:");
        code.emit("sipush 1");
        code.emit("L4:");
    }

    @Override
    public IType typecheck(Environment<IType> e) {
        IType v1 = lhs.typecheck(e);
        IType v2 = rhs.typecheck(e);
        if (v1 instanceof TypeBool && v2 instanceof TypeBool)
            return new TypeBool(((TypeBool) v1).getVal() == ((TypeBool) v2).getVal());
        else if (v1 instanceof TypeInt && v2 instanceof TypeInt)
            return new TypeBool(((TypeInt) v1).getVal() == ((TypeInt) v2).getVal());
        throw new RuntimeException(typeError("=="));
    }
}
