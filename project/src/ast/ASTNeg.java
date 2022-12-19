package ast;

import Types.IType;
import Types.TypeInt;

import static Utils.Utils.argumentError;
import static Utils.Utils.typeError;

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
                throw new RuntimeException(argumentError("-"));
        }

        public void compile(CodeBlock c, Environment<Coordinate> e) {
                typecheck(new Environment<IType>(null, 0));
                exp.compile(c, e);
                c.emit("ineg");
        }

        @Override
        public IType typecheck(Environment<IType> e) {
                IType v1 = exp.typecheck(e);
                if (v1 instanceof TypeInt)
                        return v1;

                throw new RuntimeException(typeError("-"));
        }
}
