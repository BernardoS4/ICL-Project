package ast;

import java.io.File;
import java.io.PrintStream;
import static ast.Utils.MAIN_START_CODE;
import static ast.Utils.MAIN_END_CODE;

public class Compiler {
    public static void main(String args[]) {

        Parser parser = new Parser(System.in);
        CodeBlock code = new CodeBlock();
        Environment<Coordinate> env = new Environment<>(null, -1);
        Environment<IType> e = new Environment<>(null, -1);
        String path = "project/Result.j";

        while (true) {
            try {
                ASTNode ast = parser.Start();
                PrintStream ps = new PrintStream(
                        new File(path));
                code.emit(MAIN_START_CODE);
                ast.typecheck(e);
                ast.compile(code, env);
                code.emit(MAIN_END_CODE);
                code.dump(ps);

            } catch (Exception exep) {
                System.out.println("Syntax Error!");
                exep.printStackTrace();
                parser.ReInit(System.in);
            }
        }
    }
}
