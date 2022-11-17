package ASTs;

public class ASTNum implements ASTNode {

        private VInt val;

        public ASTNum(VInt n) {
                val = n;
        }

        public VInt eval(Environment<IValue> e) {
                return val;
        }

        @Override
        public void compile(CodeBlock code, Environment<Coordinate> e) {
                code.emit("sipush" + val);
        }

}
