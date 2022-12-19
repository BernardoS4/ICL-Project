package ast;

import java.io.File;
import java.io.PrintStream;

import parser.Parser;

public class Compiler {
    public static void main(String args[]) {

        Parser parser = new Parser(System.in);
        CodeBlock code = new CodeBlock();
        Environment<Coordinate> env = new Environment<>(null, 0);

        while (true) {
            try {
                ASTNode ast = parser.Start();
                ast.compile(code, env);
                code.dump(new PrintStream(new File("./BASE-0/Result.txt")));
            } catch (Exception e) {
                System.out.println("Syntax Error!");
                e.printStackTrace();
                parser.ReInit(System.in);
            }
        }
    }
}
