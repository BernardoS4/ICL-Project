package ast;

public class ASTPrint implements ASTNode {

    ASTNode val;

    public ASTPrint(ASTNode val) {
        this.val = val;
    }

    public IValue eval(Environment<IValue> e) {
        IValue v1 = val.eval(e);
        System.out.println(v1.show());
        return v1;
    }

    @Override
    public void compile(CodeBlock code, Environment<Coordinate> e) {
        val.compile(code, e);
        code.emit("sipush" + val);
        code.emit("istore_1");
        code.emit("getstatic java/lang/System.out Ljava/io/PrintStream");
        code.emit("iload_1");
        code.emit("invokespecial java/io/PrintStream.println (I)V");
    }
}
