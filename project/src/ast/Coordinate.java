package ast;

public class Coordinate {

    private int level;
    private String id;

    public Coordinate(int level, String id) {
        this.level = level;
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public String getId() {
        return id;
    }

}
