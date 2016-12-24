.COMPUTE_MAXS

.bytecode #V1_7
.access #ACC_PUBLIC
.name #Demo
.supername #java/lang/Object
.classVisit

.methodAccess #ACC_PUBLIC
.methodName #<init>
.methodDesc #(I)V
.methodVisit
    ALOAD 0
    INVOKESPECIAL #java/lang/Object #<init> #()V
    RETURN
.visitMaxs 1 1
.methodEnd

.methodAccess #ACC_PUBLIC #ACC_STATIC
.methodName #main
.methodDesc #([Ljava/lang/String;)V
.methodVisit
    GETSTATIC #java/lang/System #out #Ljava/io/PrintStream;
    LDC #[Ljava/lang/String; "Hello World!"
    INVOKEVIRTUAL #java/io/PrintStream #println #(Ljava/lang/String;)V
    RETURN
.visitMaxs 2 1
.methodEnd

.end



