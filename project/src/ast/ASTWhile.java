package ast;

public class ASTWhile implements ASTNode {

    ASTNode cond, exp;

    public ASTWhile(ASTNode cond, ASTNode exp) {
        this.cond = cond;
        this.exp = exp;
    }

    public IValue eval(Environment<IValue> e) {
        IValue v1 = cond.eval(e);

        if (v1 instanceof VBool) {
            IValue v2 = null;
            while (((VBool) v1).getVal()) {
                v2 = exp.eval(e);
                System.out.println(v2.show());
                v1 = cond.eval(e);
            }
            return v2;
        }
        throw new RuntimeException("illegal arguments to while operation");
    }

    @Override
    public void compile(CodeBlock code, Environment<Coordinate> e) {
        code.emit("L1: ");
        // aqui nao ta bem
        // supostamente o lhs.compile eh dentro do L1
        // lhs.compile(code, e);
        code.emit("ifeq L2");
        // rhs.compile(code, e);
        code.emit("pop");
        code.emit("goto L1");
        code.emit("L2:");
    }

}
