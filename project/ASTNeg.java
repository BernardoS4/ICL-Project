public class ASTNeg {

        private ASTNode exp;

        public ASTNeg(ASTNode n) {
                exp = n;
        }

        public int eval(Environment e) {
                int v1 = exp.eval(e);
                return -v1;
        }

        public void compile(CodeBlock c) {
                c.emit("ineg");
        }
}
