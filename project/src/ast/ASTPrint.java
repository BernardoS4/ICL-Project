package ast;

import Types.IType;
import Types.TypeBool;
import Types.TypeInt;

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
        code.emit("getstatic java/lang/System.out Ljava/io/PrintStream");
        val.compile(code, e);
        code.emit("invokestatic java/lang/String/valueOf(I)Ljava/lang/String");
        code.emit("invokevirtual java/io/PrintStream.println(Ljava/lang/String;)V");
    }

    @Override
    public IType typecheck(Environment<IType> e) {
        IType v1 = val.typecheck(e);
        if (v1 instanceof TypeInt || v1 instanceof TypeBool) {
            return v1;
        }
        throw new RuntimeException("illegal arguments types to print operator");
    }
}
