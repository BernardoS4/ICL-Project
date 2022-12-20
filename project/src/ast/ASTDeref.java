package ast;

import static ast.Utils.typeError;

public class ASTDeref implements ASTNode {

    ASTNode val;

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
        IType type = typecheck(new Environment<IType>(null, 0));
        String refType = "";
        String typeJ = "";
        if (type instanceof TypeInt) {
            refType = "int";
            typeJ = "I";

        } else if (type instanceof TypeBool) {
            refType = "bool";
            typeJ = "Z";
        }
        val.compile(code, e);
        code.emit("getfield ref_of_" + refType + "/v " + typeJ);
    }

    @Override
    public IType typecheck(Environment<IType> e) {
        IType v1 = val.typecheck(e);

        if (v1 instanceof TypeRef) {
            IType refType = ((TypeRef) v1).getVal();
            return refType;
        }
        throw new RuntimeException(typeError("!"));
    }
}
