import java.io.File;
import java.io.PrintStream;

public class Compiler {
    public static void main(String args[]) {

        Parser parser = new Parser(System.in);
        CodeBlock code = new CodeBlock();
        Environment env = null;

        while (true) {
            try {
                ASTNode ast = parser.Start(env);
                ast.compile(code);
                code.dump(new PrintStream(new File("./BASE-0/Result.txt")));
            } catch (Exception e) {
                System.out.println("Syntax Error!");
                parser.ReInit(System.in);
            }
        }
    }
}
