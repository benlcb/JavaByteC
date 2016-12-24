.COMPUTE_MAXS

.bytecode #V1_7
.access #ACC_PUBLIC
.name #Demo
.supername #java/lang/Object
.classVisit

.FIELDACC #ACC_PRIVATE
.FIELDVISIT #I #var1

.methodAccess #ACC_PUBLIC
.methodName #<init>
.methodDesc #()V
.methodVisit
    ALOAD 0
    INVOKESPECIAL #java/lang/Object #<init> #()V
    ALOAD 0
    ICONST_0
    PUTFIELD #Demo #var1 #I
    ALOAD 0
    ICONST_0
    PUTFIELD #Demo #var1 #I
    RETURN
.visitMaxs 2 1

.methodAccess #ACC_PUBLIC
.methodName #<init>
.methodDesc #(I)V
.methodVisit
    ALOAD 0
    INVOKESPECIAL #java/lang/Object #<init> #()V
    ALOAD 0
    ICONST_0
    PUTFIELD #Demo #var1 #I
    ALOAD 0
    ILOAD 1
    PUTFIELD #Demo #var1 #I
    RETURN
.visitMaxs 2 2
.methodEnd

.methodName #printVar1
.methodDesc #()V
.methodVisit
    ALOAD 0
    GETFIELD #Demo #var1 #I
    BIPUSH 10
    IF_ICMPLE #L1
    NEW #java/io/UnsupportedEncodingException
    DUP
    INVOKESPECIAL #java/io/UnsupportedEncodingException #<init> #()V
    ATHROW
LABEL #L1
    FRAME #SAME
    GETSTATIC #java/lang/System #out #Ljava/io/PrintStream;
    ALOAD 0
    GETFIELD #Demo #var1 #I
    INVOKEVIRTUAL #java/io/PrintStream #println #(I)V
    RETURN
.visitMaxs 2 1
.methodEnd

.methodAccess #ACC_PUBLIC #ACC_STATIC
.methodName #main
.methodDesc #([Ljava/lang/String;)V
.methodVisit
    TRYCATCHBLOCK #L0 #L1 #L2 #java/io/UnsupportedEncodingException
    ALOAD 0
    ICONST_0
    AALOAD
    INVOKESTATIC #java/lang/Integer #parseInt #(Ljava/lang/String;)I
    ISTORE 1
    NEW #Demo
    DUP
    ILOAD 1
    INVOKESPECIAL #Demo #<init> #(I)V
    ASTORE 2
LABEL #L0
    ALOAD 2
    INVOKEVIRTUAL #Demo #printVar1 #()V
LABEL #L1
    GOTO #L5
LABEL #L2
    FRAME #FULL 3 {[Ljava/lang/String; .INTEGER Demo} 1 {java/io/UnsupportedEncodingException}
    ASTORE 3
    GETSTATIC #java/lang/System #out #Ljava/io/PrintStream;
    ICONST_M1
    INVOKEVIRTUAL #java/io/PrintStream #println #(I)V
LABEL #L5
    LINENUMBER 32 #L5
    FRAME #SAME 
    RETURN
.visitMaxs 3 3
.methodEnd

.methodAccess #ACC_PUBLIC #ACC_STATIC
.methodName #test
.methodDesc #(I)I
.methodVisit
    TRYCATCHBLOCK #L0 #L1 #L2 #java/io/UnsupportedEncodingException
LABEL   #L3
    NEW #Demo
    DUP
    ILOAD 0
    INVOKESPECIAL #Demo #<init> #(I)V
    ASTORE 1
LABEL   #L0
    ALOAD 1
    INVOKEVIRTUAL #Demo #printVar1 #()V
LABEL   #L4
    ILOAD 0
LABEL   #L1
    IRETURN
LABEL   #L2
   FRAME #FULL 2 {.INTEGER Demo} 1 {java/io/UnsupportedEncodingException}
    ASTORE 2
LABEL   #L5
    ICONST_M1
    IRETURN
LABEL   #L6
.visitMaxs 3 3
.methodEnd

.end