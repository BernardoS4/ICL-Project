package ast;

import static ast.Utils.argumentError;
import static ast.Utils.typeError;

public class ASTAssign implements ASTNode {

    ASTNode lhs, rhs;

    public ASTAssign(ASTNode l, ASTNode r) {
        lhs = l;
        rhs = r;
    }

    @Override
    public IValue eval(Environment<IValue> e) {
        IValue v1 = lhs.eval(e);
        if (v1 instanceof VCell) {
            IValue v2 = rhs.eval(e);
            ((VCell) v1).set(v2);
            return v2;
        }
        throw new RuntimeException(argumentError(":="));
    }

    @Override
    public void compile(CodeBlock code, Environment<Coordinate> e) {
        IType type = typecheck(new Environment<>(null, 0));
        String refType = "";
        String typeJ = "";
        if (type instanceof TypeInt) {
            refType = "int";
            typeJ = "I";

        } else if (type instanceof TypeBool) {
            refType = "bool";
            typeJ = "Z";
        }
        lhs.compile(code, e);
        rhs.compile(code, e);
        code.emit("putfield ref_" + refType + "/v " + typeJ);
    }

    @Override
    public IType typecheck(Environment<IType> e) {
        IType v1 = lhs.typecheck(e);
        if (v1 instanceof TypeRef) {
            IType v2 = rhs.typecheck(e);
            return v2;
        }
        throw new RuntimeException(typeError(":="));
    }
}
