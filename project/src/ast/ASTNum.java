package ast;

public class ASTNum implements ASTNode {

        private int val;

        public ASTNum(int n) {
                val = n;
        }

        public VInt eval(Environment<IValue> e) {
                return new VInt(val);
        }

        @Override
        public void compile(CodeBlock code, Environment<Coordinate> e) {
                code.emit("sipush" + val);
        }

}
