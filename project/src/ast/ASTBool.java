package ast;

public class ASTBool implements ASTNode {

        private boolean val;

        public ASTBool(boolean n) {
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
