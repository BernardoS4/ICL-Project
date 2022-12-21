package ast;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

public class CodeBlock {
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
        for (String line : code) {
            f.println(line);
            System.out.println(line);
        }
    }
}
