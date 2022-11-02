public class ASTId implements ASTNode {

    String id;

    public int eval(Environment e) {
            return e.find(id);
    }

    public ASTId(String id){
        this.id = id;
    }



}
