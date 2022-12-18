package ast;

public class ASTNeg implements ASTNode {

        private ASTNode exp;

        public ASTNeg(ASTNode n) {
                exp = n;
        }

        public IValue eval(Environment<IValue> e) {
                IValue v1 = exp.eval(e);
                if (v1 instanceof VInt) {
                        int res = -((VInt) v1).getVal();
                        return new VInt(res);
                }
                throw new RuntimeException("illegal arguments to - operator");
        }

        public void compile(CodeBlock c, Environment<Coordinate> e) {
                exp.compile(c, e);
                c.emit("ineg");
        }
}
