package Types;


public class TypeInt implements IType {

    private int v;

    public TypeInt(int v0) {
        this.v = v0;
    }

    public int getVal() {
        return v;
    }

    @Override
    public String toStr() {
        return Integer.toString(v);
    }
    
}
