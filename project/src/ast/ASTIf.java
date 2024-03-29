package ast;

import static ast.Utils.argumentError;
import static ast.Utils.typeError;

public class ASTIf implements ASTNode {

    ASTNode cond, lhs, rhs;

    public ASTIf(ASTNode cond, ASTNode lhs, ASTNode rhs) {
        this.cond = cond;
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public IValue eval(Environment<IValue> e) {
        IValue v1 = cond.eval(e);
        IValue v2;

        if (v1 instanceof VBool) {
            if (((VBool) v1).getVal()) {
                v2 = lhs.eval(e);
            } else
                v2 = rhs.eval(e);
            return v2;
        }
        throw new RuntimeException(argumentError("if"));
    }

    @Override
    public void compile(CodeBlock code, Environment<Coordinate> e) {
        cond.compile(code, e);
        code.emit("ifeq L9");
        lhs.compile(code, e);
        code.emit("goto L10");
        code.emit("L9:");
        rhs.compile(code, e);
        code.emit("L10:");
    }

    @Override
    public IType typecheck(Environment<IType> e) {
        IType v1 = cond.typecheck(e);
        if (v1 instanceof TypeBool) {
            if (((TypeBool) v1).getVal()) {
                v1 = lhs.typecheck(e);
            } else
                v1 = rhs.typecheck(e);
            return v1;
        }
        throw new RuntimeException(typeError("if"));
    }
}
