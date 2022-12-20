package ast;

import java.io.File;
import java.io.PrintStream;
import static ast.Utils.MAIN_START_CODE;
import static ast.Utils.MAIN_END_CODE;

public class Compiler {
    public static void main(String args[]) {

        Parser parser = new Parser(System.in);
        CodeBlock code = new CodeBlock();
        Environment<Coordinate> env = new Environment<>(null, 0);

        while (true) {
            try {
                ASTNode ast = parser.Start();
                PrintStream ps = new PrintStream(
                        new File("/Users/nedzero/Documents/GitHub/ICL-Project/project/src/Result.j"));
                code.emit(MAIN_START_CODE);
                ast.compile(code, env);
                code.emit(MAIN_END_CODE);
                code.dump(ps);

            } catch (Exception e) {
                System.out.println("Syntax Error!");
                e.printStackTrace();
                parser.ReInit(System.in);
            }
        }
    }
}
