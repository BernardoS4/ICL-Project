package ASTs;

public class VBool implements IValue {

    private Boolean v;

    public VBool(Boolean v0) {
        this.v = v0;
    }

    public boolean getVal() {
        return v;
    }

    @Override
    public String show() {
        return v.toString();
    }

}
