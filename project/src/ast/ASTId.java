package ast;

import static ast.Utils.*;

public class ASTId implements ASTNode {

    String id;
    String typeJ = "";

    public ASTId(String id) {
        this.id = id;
    }

    public IValue eval(Environment<IValue> e) {
        return e.find(id);
    }

    @Override
    public void compile(CodeBlock code, Environment<Coordinate> e) {
        Coordinate c = e.find(id);
        int level_shift = e.depth() - c.getLevel();
        String frame = "";
        String prev_frame = level_shift == 0 ? code.gensym(FRAME_PREFIX, e.depth()) : "";

        code.emit(ALOAD_3);
        int currentLevel = e.depth();
        for (int i = level_shift; i > 0; i--) {

            frame = code.gensym(FRAME_PREFIX, currentLevel);
            prev_frame = code.gensym(FRAME_PREFIX, currentLevel - 1);
            code.emit(Utils.changeFrames(frame, prev_frame));
            currentLevel--;
        }
        String valId = c.getId();

        code.emit(Utils.getFieldVal(prev_frame, valId, typeJ));
    }

    @Override
    public IType typecheck(Environment<IType> e) {
        IType type = e.find(id);
        IType r = type;
        if (type instanceof TypeInt) {
            typeJ = "I";
        } else if (type instanceof TypeBool) {
            typeJ = "Z";
        } else if (type instanceof TypeRef) {
            typeJ = "Lref_of_";
            type = ((TypeRef) type).getVal();

            while (type instanceof TypeRef) {
                typeJ += "ref_of_";
                type = ((TypeRef) type).getVal();
            }

            if (type instanceof TypeInt) {
                typeJ += "int;";
            } else {
                typeJ += "bool;";
            }
        }
        return r;
    }

}
