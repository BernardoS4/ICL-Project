package ast;

import java.util.HashMap;
import java.util.Map;

public class Environment<X> {

    private Environment<X> ancestor;
    private Map<String, X> defs;
    private int depth;

    public Environment(Environment<X> e, int depth) {
        this.ancestor = e;
        this.depth = depth;
        defs = new HashMap<>();
    }

    public Environment<X> beginScope() {
        return new Environment<X>(this, depth + 1);
    }

    public Environment<X> endScope() {
        return ancestor;
    }

    public int depth() {
        return depth;
    }

    public void assoc(String id, X bind) {
        defs.put(id, bind);
    }

    public X find(String id) {
        if (defs.containsKey(id))
            return defs.get(id);
        else if (ancestor != null)
            return ancestor.find(id);
        throw new RuntimeException("Variable " + id + " not found");
    }

}
