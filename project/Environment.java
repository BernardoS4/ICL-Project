import java.util.HashMap;
import java.util.Map;

public class Environment {

    Environment ancestor;

    Map<String, Integer> defs;


    public Environment(Environment e){
        this.ancestor = e;
        defs = new HashMap<String, Integer>();
    }
    
    public Environment beginScope(){
        return new Environment(this);
    }

    public Environment endScope(){
        return ancestor;
    }

    public void assoc(String id, int val){
        defs.put(id, val);
    }

    public int find(String id){
        if(!defs.containsKey(id))
            return ancestor.find(id);
        return defs.get(id);
    }
}


