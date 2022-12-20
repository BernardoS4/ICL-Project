package ast;

public class TypeBool implements IType {

    private Boolean v;

    public TypeBool(Boolean v0) {
        this.v = v0;
    }

    public boolean getVal() {
        return v;
    }

    @Override
    public String toStr() {
        return v.toString();
    }

}
