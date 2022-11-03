package ASTs;

public class ASTNeg implements ASTNode {

        private ASTNode exp;

        public ASTNeg(ASTNode n) {
                exp = n;
        }

        public int eval(Environment<Integer> e) {
                int v1 = exp.eval(e);
                return -v1;
        }

        public void compile(CodeBlock c, Environment<Coordinate> e) {
                c.emit("ineg");
        }
}
