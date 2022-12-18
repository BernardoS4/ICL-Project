package ASTs;

import Types.IType;

public class ASTDeref implements ASTNode {

    ASTNode val;

    public IValue eval(Environment<IValue> e) {
        IValue v1 = val.eval(e);
        System.out.println(v1.show());
        return v1;
    }

    public IType typecheck(Environment<IType> env) {
        IType v1 = val.typecheck(env);

        if (v1 instanceof RefType) {
            Type reftype = ((RefType)v1).getType();
            return reftype;
        } 
        else 
            throw new Exception("illegal argument type to ! operator");
    }

    public ASTDeref(ASTNode val) {
        this.val = val;
    }

    @Override
    public void compile(CodeBlock code, Environment<Coordinate> e) {
        val.compile(code, e);
        code.emit("");
    }
}
