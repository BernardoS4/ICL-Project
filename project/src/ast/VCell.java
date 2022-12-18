package ast;

public class VCell implements IValue {

    private IValue v;

    public VCell(IValue v0) {
        this.v = v0;
    }

    public IValue getVal() {
        return v;
    }

    public void set(IValue v0) {
        this.v = v0;
    }

    @Override
    public String show() {
        return v.toString();
    }
}
