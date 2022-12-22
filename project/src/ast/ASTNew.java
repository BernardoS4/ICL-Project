package ast;

public class ASTNew implements ASTNode {

    private ASTNode val;
    private String refType = "";
    private String typeJ = "";

    public ASTNew(ASTNode val) {
        this.val = val;
    }

    public IValue eval(Environment<IValue> e) {
        IValue v = val.eval(e);
        return new VCell(v);
    }

    @Override
    public void compile(CodeBlock code, Environment<Coordinate> e) {
        Utils.defRefFile(refType, typeJ);
        code.emit("new " + refType);
        code.emit("dup");
        code.emit("invokespecial " + refType + "/<init>()V");
        code.emit("dup");
        val.compile(code, e);
        code.emit("putfield " + refType + "/v " + typeJ);
    }

    @Override
    public IType typecheck(Environment<IType> e) {
        IType v1 = val.typecheck(e);

        refType += "ref_of_";
        typeJ += "Lref_of_";

        if (v1 instanceof TypeBool) {
            refType += "bool";
            typeJ = "Z";
        } else if (v1 instanceof TypeInt) {
            refType += "int";
            typeJ = "I";
        } else {
            v1 = ((TypeRef) v1).getVal();
            while (v1 instanceof TypeRef) {
                refType += "ref_of_";
                typeJ += "ref_of_";
                v1 = ((TypeRef) v1).getVal();
            }
            if (v1 instanceof TypeInt) {
                refType += "int";
                typeJ += "int;";
            } else {
                refType += "bool";
                typeJ += "bool;";
            }
        }
        return new TypeRef(v1);
    }
}
