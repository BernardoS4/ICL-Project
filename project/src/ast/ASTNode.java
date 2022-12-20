package ast;

public interface ASTNode {

    IValue eval(Environment<IValue> e);

    void compile(CodeBlock code, Environment<Coordinate> e);

    IType typecheck(Environment<IType> e);
}
