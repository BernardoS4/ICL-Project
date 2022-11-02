import java.util.HashMap;
import java.util.Map;

public class Environment {

    private Environment ancestor;
    private Map<String, Integer> defs;

    public Environment(Environment e) {
        this.ancestor = e;
        defs = new HashMap<String, Integer>();
    }

    public Environment beginScope() {
        return new Environment(this);
    }

    public Environment endScope() {
        return ancestor;
    }

    public void assoc(String id, int val) {
        defs.put(id, val);
    }

    public int find(String id) {
        if (defs.containsKey(id))
            return defs.get(id);
        else if (ancestor != null)
            return ancestor.find(id);
        throw new RuntimeException("Variable " + id + " not found");
    }

}
