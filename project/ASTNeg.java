public class ASTNeg {
    
        int val;

        public int eval(Environment e) {
                return val;
        }

        public ASTNeg(int n) {
                val = -n;
        }


}
