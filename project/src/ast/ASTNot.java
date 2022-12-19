package ast;

import Types.IType;
import Types.TypeBool;

import static Utils.Utils.argumentError;
import static Utils.Utils.typeError;

public class ASTNot implements ASTNode {

    ASTNode val;

    public IValue eval(Environment<IValue> e) {
        IValue v1 = val.eval(e);

        if (v1 instanceof VBool) {
            return new VBool(!((VBool) v1).getVal());
        }
        throw new RuntimeException(argumentError("~"));
    }

    public ASTNot(ASTNode val) {
        this.val = val;
    }

    @Override
    public void compile(CodeBlock code, Environment<Coordinate> e) {
        val.compile(code, e);
        code.emit("ifeq L1");
        code.emit("sipush 0");
        code.emit("L1: sipush 1");
    }

    @Override
    public IType typecheck(Environment<IType> e) {
        IType v1 = val.typecheck(e);
        if (v1 instanceof TypeBool)
            return v1;

        throw new RuntimeException(typeError("~"));
    }
}
