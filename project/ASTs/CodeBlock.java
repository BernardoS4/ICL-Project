package ASTs;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

public class CodeBlock {
    private static final String PREAMBULE = " . . . ";
    private static final String POS = " . . . ";

    List<String> code;

    public CodeBlock() {
        code = new LinkedList<String>();
    }

    public void emit(String opcode) {
        code.add(opcode);
    }

    public String gensym(String prefix, int level) {
        return prefix.concat(String.valueOf(level));
    }

    void dump(PrintStream f) {
        f.println(PREAMBULE);
        for (String line : code) {
            f.println(line);
            System.out.println(line);
        }
        f.println(POS);
    }
}
