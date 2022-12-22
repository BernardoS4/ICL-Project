package ast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Utils {

    public static final String MAIN_START_CODE = ".class public Main\n" +
            ".super java/lang/Object\n" +
            ";\n" +
            "; standard initializer\n" +
            ".method public <init>()V\n" +
            "aload_0\n" +
            "invokenonvirtual java/lang/Object/<init>()V\n" +
            "return\n" +
            ".end method\n" +
            ".method public static main([Ljava/lang/String;)V\n" +
            "; set limits used by this method\n" +
            ".limit locals 10\n" +
            ".limit stack 256\n" +
            "; setup local variables:\n" +
            ";1 - the PrintStream object held in java.lang.System.out\n" +
            "getstatic java/lang/System/out Ljava/io/PrintStream;\n" +
            "; place bytecodes here\n" +
            "aconst_null\n" +
            "astore_3\n" +
            "; START";

    public static final String MAIN_END_CODE = "; END\n" +
            "return\n" +
            ".end method\n";

    public static final String genericPath = "project/src/";

    /**
     * 
     */
    public static final String IADD = "iadd";
    public static final String ISUB = "isub";
    public static final String IDIV = "idiv";
    public static final String IMUL = "imul";
    public static final String SIPUSH = "sipush";

    /**
     * 
     */
    public static final String ALOAD_3 = "aload_3";
    public static final String ASTORE_3 = "astore_3";
    public static final String PUT_FIELD = "putfield ";
    public static final String GET_FIELD = "getfield ";

    public static final String FRAME_PREFIX = "frame_";
    public static final String FIELD_PREFIX = "v";
    public static final String SLASH = "/";
    public static final String SL = "/sl L";

    public static String putFrameVal(String frame, String field, String type) {
        return PUT_FIELD + frame + SLASH + field + " " + type;
    }

    public static String changeFrames(String frame, String old_frame) {
        return GET_FIELD + frame + SL + old_frame + ";";
    }

    public static String getFieldVal(String old_frame, String valId, String type) {
        return GET_FIELD + old_frame + "/" + valId + " " + type;
    }

    public static String argumentError(String operator) {
        return "illegal arguments to " + operator + " operator";
    }

    public static String typeError(String operator) {
        return "illegal arguments types to " + operator + " operator";
    }

    public static void defFrameFile(String frame, String oldFrame, String variables) {
        try (PrintStream ps = new PrintStream(new File(genericPath + frame + ".j"))) {
            CodeBlock code = new CodeBlock();

            String a = ".class public " + frame + "\n" +
                    ".super java/lang/Object\n" +
                    ".field public sl L" + oldFrame + "\n" +
                    variables +
                    ".method public <init>()V\n" +
                    "aload_0\n" +
                    "invokenonvirtual java/lang/Object/<init>()V\n" +
                    "return\n" +
                    ".end method\n";

            code.emit(a);
            code.dump(ps);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void defRefFile(String refType, String type) {
        try (PrintStream ps = new PrintStream(new File(genericPath + refType + ".j"))) {
            CodeBlock code = new CodeBlock();

            String a = ".class public " + refType + "\n" +
                    ".super java/lang/Object\n" +
                    ".field public v " + type + "\n" +
                    ".method public <init>()V\n" +
                    "aload_0\n" +
                    "invokenonvirtual java/lang/Object/<init>()V\n" +
                    "return\n" +
                    ".end method\n";

            code.emit(a);
            code.dump(ps);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
