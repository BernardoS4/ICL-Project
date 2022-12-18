package ast;

import Types.IType;

public interface ASTNode {

    IValue eval(Environment<IValue> e);

    void compile(CodeBlock code, Environment<Coordinate> e);

}
