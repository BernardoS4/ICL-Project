public class ASTId implements ASTNode {

    String id;

    public ASTId(String id) {
        this.id = id;
    }

    public int eval(Environment e) {
        return e.find(id);
    }

    @Override
    public void compile(CodeBlock code) {
        // TODO Auto-generated method stub
        
    }

}
