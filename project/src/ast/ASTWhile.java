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
        typecheck(new Environment<IType>(null, 0));
        code.emit("L1:");
        cond.compile(code, e);
        code.emit("ifeq L2");
        exp.compile(code, e);
        code.emit("pop");
        code.emit("goto L1");
        code.emit("L2:");
    }

    @Override
    public IType typecheck(Environment<IType> e) {
        IType v1 = cond.typecheck(e);
        exp.typecheck(e);
        if (v1 instanceof TypeBool)
            return v1;

        throw new RuntimeException("illegal arguments types to while operator");
    }

}
