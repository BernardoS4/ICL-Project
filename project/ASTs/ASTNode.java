package ASTs;

public interface ASTNode {

    int eval(Environment<Integer> e);

    void compile(CodeBlock code, Environment<Coordinate> e);
}
