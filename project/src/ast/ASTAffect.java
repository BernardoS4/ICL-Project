package ast;

public class ASTAffect implements ASTNode {

    public ASTAffect(ASTNode t1, ASTNode t2) {
    }

    @Override
    public IValue eval(Environment<IValue> e) {
        return null;
    }

    @Override
    public void compile(CodeBlock code, Environment<Coordinate> e) {
    }
}
