package ast;

public class ASTWhile implements ASTNode {

    ASTNode cond, exp;

    public ASTWhile(ASTNode cond, ASTNode exp) {
        this.cond = cond;
        this.exp = exp;
    }

    public IValue eval(Environment<IValue> e) {
        IValue v1 = cond.eval(e);
        IValue v2 = v1;

        if (v1 instanceof VBool) {
            while (((VBool) v1).getVal()) {
                v2 = exp.eval(e);
                v1 = cond.eval(e);
            }
            return v2;
        }
        throw new RuntimeException("illegal arguments to while operation");
    }

    @Override
    public void compile(CodeBlock code, Environment<Coordinate> e) {
        code.emit("L17:");
        cond.compile(code, e);
        code.emit("ifeq L18");
        exp.compile(code, e);
        code.emit("pop");
        code.emit("goto L17");
        code.emit("L18:");
    }

    @Override
    public IType typecheck(Environment<IType> e) {
        IType v1 = cond.typecheck(e);
        if (v1 instanceof TypeBool) {
            exp.typecheck(e);
            return new TypeBool(false);
        }
        throw new RuntimeException("illegal arguments types to while operator");
    }

}
