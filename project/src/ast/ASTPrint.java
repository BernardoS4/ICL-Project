package ast;

import Types.IType;

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
        typecheck(new Environment<IType>(null, 0));
        code.emit("getstatic java/lang/System.out Ljava/io/PrintStream");
        val.compile(code, e);
        code.emit("invokestatic java/lang/String/valueOf(I)Ljava/lang/String");
        code.emit("invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V");
    }

    @Override
    public IType typecheck(Environment<IType> e) {
        IType v1 = val.typecheck(e);
        return v1;
    }
}
