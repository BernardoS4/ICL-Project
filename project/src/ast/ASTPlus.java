package ast;

public class ASTPlus implements ASTNode {

        private ASTNode lhs, rhs;

        public IValue eval(Environment<IValue> e) {
                IValue v1 = lhs.eval(e);
                if (v1 instanceof VInt) {
                        IValue v2 = rhs.eval(e);
                        if (v2 instanceof VInt) {
                                return new VInt(((VInt) v1).getVal() + ((VInt) v2).getVal());
                        }
                }
                throw new RuntimeException("illegal arguments to + operator");

        }

        public ASTPlus(ASTNode l, ASTNode r) {
                lhs = l;
                rhs = r;
        }

        @Override
        public void compile(CodeBlock code, Environment<Coordinate> e) {

                lhs.compile(code, e);
                rhs.compile(code, e);
                code.emit("iadd");
        }
}
