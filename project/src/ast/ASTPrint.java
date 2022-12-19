package ast;

public class ASTPrint implements ASTNode {

    ASTNode val;

    public ASTPrint(ASTNode val) {
        this.val = val;
    }

    public IValue eval(Environment<IValue> e) {
        IValue v1 = val.eval(e);
        return v1;
    }

    @Override
    public void compile(CodeBlock code, Environment<Coordinate> e) {
        code.emit("sipush" + val);
        code.emit("istore_1");
        code.emit("getstatic java/lang/System.out Ljava/io/PrintStream");
        val.compile(code, e);
        code.emit("iload_1");
        code.emit("invokestatic java/lang/String/valueOf(I)Ljava/lang/String");
        code.emit("invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V");
    }
}
