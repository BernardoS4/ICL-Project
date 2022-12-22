package ast;

import static ast.Utils.argumentError;
import static ast.Utils.typeError;

public class ASTPlus implements ASTNode {

        private ASTNode lhs, rhs;

        public ASTPlus(ASTNode l, ASTNode r) {
                lhs = l;
                rhs = r;
        }

        public IValue eval(Environment<IValue> e) {
                IValue v1 = lhs.eval(e);
                IValue v2;

                if (v1 instanceof VInt) {
                        v2 = rhs.eval(e);
                        if (v2 instanceof VInt) {
                                return new VInt(((VInt) v1).getVal() + ((VInt) v2).getVal());
                        }
                }
                throw new RuntimeException(argumentError("+"));

        }

        @Override
        public void compile(CodeBlock code, Environment<Coordinate> e) {
                lhs.compile(code, e);
                rhs.compile(code, e);
                code.emit("iadd");
        }

        @Override
        public IType typecheck(Environment<IType> e) {
                IType v1 = lhs.typecheck(e);
                if (v1 instanceof TypeInt) {
                        IType v2 = rhs.typecheck(e);
                        if (v2 instanceof TypeInt)
                                return v1;
                }
                throw new RuntimeException(typeError("+"));
        }
}
