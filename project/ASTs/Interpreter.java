package ASTs;

import Parser.Parser;

public class Interpreter {

    public static void main(String args[]) {
        Parser parser = new Parser(System.in);
        ASTNode exp;
        Environment env = null;

        while (true) {
            try {
                exp = parser.Start(env);
                System.out.println(exp.eval(env));
            } catch (Exception e) {
                System.out.println("Syntax Error!");
                parser.ReInit(System.in);
            }
        }
    }
}
