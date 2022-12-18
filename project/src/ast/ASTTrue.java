package ast;

public class ASTTrue implements ASTNode {

        private boolean val;

        public ASTTrue(boolean n) {
                val = n;
        }

        public VBool eval(Environment<IValue> e) {
                return new VBool(val);
        }

        @Override
        public void compile(CodeBlock code, Environment<Coordinate> e) {
                code.emit("sipush" + val);
        }

}
