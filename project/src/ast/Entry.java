package ast;

public class Entry {

    String key;
    ASTNode value;

    public Entry(String key, ASTNode value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ASTNode getValue() {
        return value;
    }

    public void setValue(ASTNode value) {
        this.value = value;
    }

}
