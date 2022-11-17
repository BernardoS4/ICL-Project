package ASTs;

public class VInt implements IValue {

    private int v;

    public VInt(int v0) {
        this.v = v0;
    }

    public int getVal() {
        return v;
    }

    @Override
    public String show() {
        return Integer.toString(v);
    }

}
