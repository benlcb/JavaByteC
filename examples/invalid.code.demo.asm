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
    GETSTATIC #java/lang/System #out #Ljava/io/PrintStream;
    ALOAD 0
    GETFIELD #Demos #var1 #I
    INVOKEVIRTUAL #java/io/PrintStream #println #(I)V
    RETURN
.visitMaxs 2 1
.methodEnd

.methodAccess #ACC_PUBLIC #ACC_STATIC
.methodName #main
.methodDesc #([Ljava/lang/String;)V
.methodVisit
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
    ALOAD 2
    INVOKEVIRTUAL #Demo #printVar1 #()V
    RETURN
.visitMaxs 3 3
.methodEnd

.end