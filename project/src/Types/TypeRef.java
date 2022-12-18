package Types;

public class TypeRef implements IType {

    private IType v;

    public TypeRef(IType t) {
        this.v = t;
    }

    public IType getVal() {
        return v;
    }

    @Override
    public String toStr() {
        return v.toString();
    }
    
}
