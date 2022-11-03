package ASTs;

public class ASTNum implements ASTNode {

        int val;

        public int eval(Environment<Integer> e) {
                return val;
        }

        public ASTNum(int n) {
                val = n;
        }

        @Override
        public void compile(CodeBlock code, Environment<Coordinate> e) {
                code.emit("sipush" + val);
        }

}
