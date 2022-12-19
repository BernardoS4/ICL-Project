package ast;

import Types.IType;
import Types.TypeBool;
import Types.TypeInt;

public class ASTNew implements ASTNode {

    private ASTNode val;

    public ASTNew(ASTNode val) {
        this.val = val;
    }

    public IValue eval(Environment<IValue> e) {
        IValue v = val.eval(e);
        return new VCell(v);
    }

    @Override
    public void compile(CodeBlock code, Environment<Coordinate> e) {
        IType type = typecheck(new Environment<IType>());
        String refType = "";
        String typeJ = "";
        if (type instanceof TypeInt) {
            refType = "int";
            typeJ = "I";

        } else if (type instanceof TypeBool) {
            refType = "bool";
            typeJ = "Z";
        }

        code.emit("new ref_of_" + refType);
        code.emit("dup");
        code.emit("invokespecial ref_of_" + refType + "/<init>()V");
        code.emit("dup");
        val.compile(code, e);
        code.emit("putfield ref_of_" + refType + "/ v " + typeJ);
    }

    @Override
    public IType typecheck(Environment<IType> e) {
        IType v1 = val.typecheck(e);
        if (v1 instanceof TypeInt || v1 instanceof TypeBool) {
            return v1;
        }
        throw new RuntimeException("illegal arguments types to new operator");
    }
}
