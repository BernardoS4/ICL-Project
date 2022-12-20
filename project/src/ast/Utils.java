package ast;

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
            "; convert to String;\n" +
            "invokestatic java/lang/String/valueOf(I)Ljava/lang/String;\n" +
            "; call println\n" +
            "invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V\n" +
            "return\n" +
            ".end method\n";

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
    public static final String ALOAD_3 = "aload 3";
    public static final String ASTORE_3 = "astore 3";
    public static final String PUT_FIELD = "putfield ";
    public static final String GET_FIELD = "getfield ";

    public static final String FRAME_PREFIX = "frame_";
    public static final String FIELD_PREFIX = "v";
    public static final String SLASH = "/";
    public static final String SL = "/sl";

    public static String putFrameVal(String frame, String field) {
        return PUT_FIELD + frame + SLASH + field;
    }

    public static String changeFrames(String frame, String old_frame) {
        return GET_FIELD + frame + SL + old_frame;
    }

    public static String getFieldVal(String old_frame, String valId) {
        return GET_FIELD + old_frame + valId;
    }

    public static String argumentError(String operator) {
        return "illegal arguments to " + operator + " operator";
    }

    public static String typeError(String operator) {
        return "illegal arguments types to " + operator + " operator";
    }
}
