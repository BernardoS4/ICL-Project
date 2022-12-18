package ast;

import parser.Parser;

public class Interpreter {

    public static void main(String args[]) {
        Parser parser = new Parser(System.in);
        ASTNode exp;
        Environment<IValue> env = new Environment<>(null, 0);

        while (true) {
            try {
                exp = parser.Start();
                System.out.println(exp.eval(env).show());
            } catch (Exception e) {
                System.out.println("Syntax Error!");
                e.printStackTrace();
                parser.ReInit(System.in);
            }
        }
    }
}
