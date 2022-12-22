package ast;

import static ast.Utils.typeError;

public class ASTDeref implements ASTNode {

    ASTNode val;
    String refType = "";
    String typeJ = "";

    public ASTDeref(ASTNode val) {
        this.val = val;
    }

    public IValue eval(Environment<IValue> e) {
        IValue v1 = val.eval(e);

        if (v1 instanceof VCell) {
            return ((VCell) v1).getVal();
        }
        throw new RuntimeException("illegal arguments to ! operator");
    }

    @Override
    public void compile(CodeBlock code, Environment<Coordinate> e) {
        val.compile(code, e);
        code.emit("getfield ref_of_" + refType + "/v " + typeJ);
    }

    @Override
    public IType typecheck(Environment<IType> e) {
        IType v1 = val.typecheck(e);

        if (v1 instanceof TypeRef) {
            v1 = ((TypeRef) v1).getVal();

            if (v1 instanceof TypeInt) {
                refType = "int";
                typeJ = "I";

            } else if (v1 instanceof TypeBool) {
                refType = "bool";
                typeJ = "Z";
            }
            return v1;
        }

        throw new RuntimeException(typeError("!"));
    }
}
