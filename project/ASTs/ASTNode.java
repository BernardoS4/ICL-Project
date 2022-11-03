package ASTs;

public interface ASTNode {

    int eval(Environment e);

    void compile(CodeBlock code, Environment e);
}
