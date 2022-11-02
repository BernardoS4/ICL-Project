public class ASTDef {
    	
    ASTNode	body;	
    
    void eval(Environment e)	{
    
        e = e.beginScope();
    }

    public ASTDef(ASTNode body){
        this.body = body;

    }

}
