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
                int v = (val) ? 1 : 0;
                code.emit("sipush " + v);
        }

        @Override
        public IType typecheck(Environment<IType> e) {
                return new TypeBool(val);
        }
}
