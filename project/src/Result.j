.class public Main
.super java/lang/Object
;
; standard initializer
.method public <init>()V
aload_0
invokenonvirtual java/lang/Object/<init>()V
return
.end method
.method public static main([Ljava/lang/String;)V
; set limits used by this method
.limit locals 10
.limit stack 256
; setup local variables:
;1 - the PrintStream object held in java.lang.System.out
getstatic java/lang/System/out Ljava/io/PrintStream;
; place bytecodes here
aconst_null
astore_3
; START
new frame_0
dup
invokespecial frame_0/<init>()V
dup
aload_3
putfield frame_0/sl Ljava/lang/Object;
astore_3
aload_3
new ref_of_int
dup
invokespecial ref_of_int/<init>()V
dup
sipush 3
putfield ref_of_int/v I
putfield frame_0/v0 Ljava/lang/Object;
sipush 2
; END
; convert to String;
invokestatic java/lang/String/valueOf(I)Ljava/lang/String;
; call println
invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
return
.end method

