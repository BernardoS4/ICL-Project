package ast;

import static Utils.Utils.*;

import Types.IType;
import Utils.Utils;

public class ASTId implements ASTNode {

    String id;

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
        String prev_frame = "";

        // generate code to fetch id slot value
        code.emit(ALOAD_3);
        int currentLevel = e.depth();
        for (int i = level_shift; i > 0; i--) {

            frame = code.gensym(FRAME_PREFIX, currentLevel);
            prev_frame = code.gensym(FRAME_PREFIX, currentLevel - 1);
            code.emit(Utils.changeFrames(frame, prev_frame));
            currentLevel--;
        }
        String valId = c.getId();
        code.emit(Utils.getFieldVal(prev_frame, valId));
    }

    @Override
    public IType typecheck(Environment<IType> e) {
        // TODO Auto-generated method stub
        return null;
    }

}
