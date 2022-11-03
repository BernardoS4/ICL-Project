package ASTs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Environment {

    private Environment ancestor;
    private Map<String, Coordinate> defs;
    private int depth = 0;

    public Environment(Environment e) {
        this.ancestor = e;
        this.depth = e.depth() + 1;
        defs = new HashMap<String, Coordinate>();
    }

    public Environment beginScope() {
        return new Environment(this);
    }

    public Environment endScope() {
        return ancestor;
    }

    public int depth() {
        return depth;
    }

    public void assoc(String id, Coordinate bind) {
        defs.put(id, bind);
    }

    public Coordinate find(String id) {
        if (defs.containsKey(id))
            return defs.get(id);
        else if (ancestor != null)
            return ancestor.find(id);
        throw new RuntimeException("Variable " + id + " not found");
    }

}
