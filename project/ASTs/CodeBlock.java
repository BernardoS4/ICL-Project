package ASTs;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

class CodeBlock {
    private static final String PREAMBULE = " . . . ";
    private static final String POS = " . . . ";
    private int counter = 0;

    List<String> code;

    public CodeBlock() {
        code = new LinkedList<String>();
    }

    public void emit(String opcode) {
        code.add(opcode);
    }

    public String gensym(String prefix) {
        return prefix.concat(String.valueOf(counter++));
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
