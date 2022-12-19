package ast;

import Types.IType;
import Types.TypeBool;

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
                typecheck(new Environment<IType>());
                code.emit("sipush" + val);
        }

        @Override
        public IType typecheck(Environment<IType> e) {

                return null;
        }
}
