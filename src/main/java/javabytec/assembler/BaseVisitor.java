package javabytec.assembler;

import javabytec.parser.JavaByteGrammarBaseVisitor;
import javabytec.parser.JavaByteGrammarParser;
import org.objectweb.asm.*;
import static javabytec.assembler.BaseVisitor.ResetType.*;
import static javabytec.assembler.BaseVisitorHelper.*;
import static javabytec.parser.JavaByteGrammarParser.*;

public class BaseVisitor extends JavaByteGrammarBaseVisitor implements ASMConstants{

    private ClassWriter classWriter;
    private FieldVisitor fieldVisitor;
    private MethodVisitor methodVisitor;

    private int java_version = 51;
    private int classacc = 0;
    private String classname;
    private String signature;
    private String supername = "java/lang/Object";
    private String[] interfaces;

    private int methodAccess = 0;
    private String methodName;
    private String methodDesc;
    private String methodSign;
    private String[] methodExcep;

    private int fieldAccess = 0;

    enum ResetType {
        CLASS, METHOD, FIELD, ALL
    }

    private LabelMap labels;

    //--------------------------------------- Directives ----------------------------------------------
    @Override
    public Object visitDirective(JavaByteGrammarParser.DirectiveContext ctx) {

        switch (ctx.constdir().start.getType()) {
            case COMPUTEMAXS:
                classWriter = new ClassWriter(COMPUTE_MAXS);
                break;

            case COMPUTEFRAMES:
                classWriter = new ClassWriter(COMPUTE_FRAMES);
                break;

            case BYTECODE:
                try {
                    java_version = Integer.parseInt(ctx.PARAM(0).toString().substring(1));
                } catch (Exception e) {
                    java_version = (int) reflector(ctx.PARAM(0).toString().substring(1));
                }
                break;

            case ACCESS:
                classacc = accessCalc(ctx);
                break;

            case NAME:
                classname = ctx.PARAM(0).toString().substring(1);
                break;

            case SIGNATURE:
                signature = ctx.PARAM(0).toString().substring(1);
                break;

            case SUPERNAME:
                supername = ctx.PARAM(0).toString().substring(1);
                break;

            case INTERFACES:
                interfaces = new String[ctx.PARAM().size()];
                for (int i = 0; ctx.PARAM().size() > i; i++) {
                    interfaces[i] = ctx.PARAM(i).getText().substring(1);
                }
                break;

            case CLASSVISIT:
                if (ctx.getChildCount() == 2) {
                    classname = ctx.PARAM(0).toString().substring(1);
                }
                classWriter.visit(java_version, classacc, classname, signature, supername, interfaces);
                break;

            case END:
                classWriter.visitEnd();
                setDefault(CLASS);
                break;

            case METHODVISIT:
                if (ctx.getChildCount() == 3) {
                    methodName = ctx.PARAM(0).toString().substring(1);
                    methodDesc = ctx.PARAM(1).toString().substring(1);
                }
                methodVisitor = classWriter.visitMethod(methodAccess, methodName, methodDesc, methodSign, methodExcep);
                methodVisitor.visitCode();
                labels = new LabelMap(); // Default Size of 16, LF 0.75
                break;

            case METHODACCESS:
                methodAccess = accessCalc(ctx);
                break;

            case METHODNAME:
                methodName = ctx.PARAM(0).toString().substring(1);
                break;

            case METHODDESC:
                methodDesc = ctx.PARAM(0).toString().substring(1);
                break;

            case METHODSIGN:
                methodSign = ctx.PARAM(0).toString().substring(1);
                break;

            case METHODEXCEP:
                methodExcep = new String[ctx.PARAM().size()];
                for (int i = 0; ctx.PARAM().size() > i; i++) {
                    interfaces[i] = ctx.PARAM(i).getText().substring(1);
                }
                break;

            case VISITMAXS:
                int stack = Integer.parseInt(ctx.PARAM(0).toString());
                int local = Integer.parseInt(ctx.PARAM(1).toString());
                methodVisitor.visitMaxs(stack, local);
                break;

            case METHODEND:
                methodVisitor.visitEnd();
                setDefault(METHOD);
                labels = null;
                break;

            case SETDEFAULT:
                switch (ctx.PARAM(0).toString().toUpperCase()) {
                    case "#CLASS":
                        setDefault(CLASS);
                        break;
                    case "#METHOD":
                        setDefault(METHOD);
                        break;
                    case "#ALL":
                        setDefault(ALL);
                        break;
                    case "#FIELD":
                        setDefault(FIELD);
                }
                break;

            default: throw new UnsupportedOperationException();
        }
        return null;
    }


    //--------------------------------------- Instructions ----------------------------------------------

    @Override
    public Object visitInsn(JavaByteGrammarParser.InsnContext ctx){

        InstructionContext insnCtx = (InstructionContext) ctx.getParent().getParent();
        int opcVal = (int) reflector(insnCtx.getChild(0).getText().toUpperCase());

        methodVisitor.visitInsn(opcVal);

        return null;

    }

    @Override
    public Object visitFieldinsn(JavaByteGrammarParser.FieldinsnContext ctx){

        InstructionContext insnCtx = (InstructionContext) ctx.getParent().getParent();
        int opcVal = (int) reflector(insnCtx.getChild(0).getText().toUpperCase());

        String owner = insnCtx.getChild(1).getText().substring(1);
        String name = insnCtx.getChild(2).getText().substring(1);
        String desc = insnCtx.getChild(3).getText().substring(1);

        methodVisitor.visitFieldInsn(opcVal, owner, name, desc);

        return null;
    }

    @Override
    public Object visitMethodinsn(JavaByteGrammarParser.MethodinsnContext ctx){

        InstructionContext insnCtx = (InstructionContext) ctx.getParent().getParent();
        int opcVal = (int) reflector(insnCtx.getChild(0).getText().toUpperCase());

        String owner = insnCtx.getChild(1).getText().substring(1);
        String name = insnCtx.getChild(2).getText().substring(1);
        String desc = insnCtx.getChild(3).getText().substring(1);

        boolean itf = false;
        if (ctx.getChild(4)!=null) {
            itf = (insnCtx.getChild(4).getText() == "0" || insnCtx.getChild(4).getText() == "#false") ? true : false;
        }

        methodVisitor.visitMethodInsn(opcVal, owner, name, desc, itf);

        return null;
    }

    @Override
    public Object visitVarinsn(JavaByteGrammarParser.VarinsnContext ctx){

        InstructionContext insnCtx = (InstructionContext) ctx.getParent().getParent();
        int opcVal = (int) reflector(insnCtx.getChild(0).getText().toUpperCase());

        int index = Integer.parseInt(insnCtx.getChild(1).toString());
        methodVisitor.visitVarInsn(opcVal,index);

        return null;
    }

    //TODO: LDC INSN
    @Override
    public Object visitLdcinsn(JavaByteGrammarParser.LdcinsnContext ctx){

        InstructionContext insnCtx = (InstructionContext) ctx.getParent().getParent();

        String constantType = insnCtx.PARAM(0).toString().substring(1);

        String constant = insnCtx.PARAM(1).toString().replace('\"', ' ');

        methodVisitor.visitLdcInsn(constant);

        return null;
    }

    @Override
    public Object visitTypeinsn(JavaByteGrammarParser.TypeinsnContext ctx){

        InstructionContext insnCtx = (InstructionContext) ctx.getParent().getParent();
        int opcVal = (int) reflector(insnCtx.getChild(0).getText().toUpperCase());

        String type = insnCtx.getChild(1).toString().substring(1);
        methodVisitor.visitTypeInsn(opcVal, type);

        return null;
    }

    @Override
    public Object visitIntinsn(JavaByteGrammarParser.IntinsnContext ctx){

        InstructionContext insnCtx = (InstructionContext) ctx.getParent().getParent();
        int opcVal = (int) reflector(insnCtx.opcode().getText().toUpperCase());

        int operand = 0;
        try{
            operand = Integer.parseInt(insnCtx.PARAM(0).toString());

        } catch (NumberFormatException e){

            String operandDesc = insnCtx.PARAM(0).toString().substring(1);
            if (!operandDesc.startsWith("T_")) operandDesc += "T_";
            operand = (int) reflector(operandDesc);

        }

        methodVisitor.visitIntInsn(opcVal, operand);

        return null;
    }

    @Override
    public Object visitIincinsn(JavaByteGrammarParser.IincinsnContext ctx){

        InstructionContext insnCtx = (InstructionContext) ctx.getParent().getParent();

        int var = Integer.parseInt(insnCtx.PARAM(0).toString());
        int inc = Integer.parseInt(insnCtx.PARAM(1).toString());

        methodVisitor.visitIincInsn(var, inc);

        return null;
    }

    //-------------------------------------- Labels, Jumps, Line Numbers ------------------------------------

    @Override
    public Object visitLabel(JavaByteGrammarParser.LabelContext ctx){

        InstructionContext insnCtx = (InstructionContext) ctx.getParent().getParent();

        Label label = labels.getLabel(insnCtx.PARAM(0).toString());
        methodVisitor.visitLabel(label);

        return null;
    }

    @Override
    public Object visitLineNumber(JavaByteGrammarParser.LineNumberContext ctx){

        InstructionContext insnCtx = (InstructionContext) ctx.getParent().getParent();

        int line = Integer.parseInt(insnCtx.PARAM(0).toString());
        Label label = labels.getLabel(insnCtx.PARAM(0).toString());
        methodVisitor.visitLineNumber(line, label);

        return null;
    }

    @Override
    public Object visitTableSwitchInsn(JavaByteGrammarParser.TableSwitchInsnContext ctx){

        InstructionContext insnCtx = (InstructionContext) ctx.getParent().getParent();

        int min = Integer.parseInt(insnCtx.PARAM(0).toString());
        int max = Integer.parseInt(insnCtx.PARAM(1).toString());

        Label dflt = labels.getLabel(insnCtx.PARAM(2).toString());

        Label[] tLabels = labels.getLabels(insnCtx.PARAM(3).toString());

        methodVisitor.visitTableSwitchInsn(min, max, dflt, tLabels);

        return null;
    }

    @Override
    public Object visitLookupSwitchInsn(JavaByteGrammarParser.LookupSwitchInsnContext ctx){

        InstructionContext insnCtx = (InstructionContext) ctx.getParent().getParent();

        Label dflt = labels.getLabel(insnCtx.PARAM(0).toString());
        int[] keys = intArrayInterpreter(insnCtx.PARAM(1).toString());
        Label[] sLabels = labels.getLabels(insnCtx.PARAM(2).toString());

        methodVisitor.visitLookupSwitchInsn(dflt, keys, sLabels);

        return null;
    }

    @Override
    public Object visitJumpInsn(JavaByteGrammarParser.JumpInsnContext ctx){

        InstructionContext insnCtx = (InstructionContext) ctx.getParent().getParent();
        int opcVal = (int) reflector(insnCtx.opcode().getText().toUpperCase());

        Label label = labels.getLabel(insnCtx.PARAM(0).toString());

        methodVisitor.visitJumpInsn(opcVal, label);

        return null;
    }

    @Override
    public Object visitTryCatchBlock(JavaByteGrammarParser.TryCatchBlockContext ctx){

        InstructionContext insnCtx = (InstructionContext) ctx.getParent().getParent();

        Label start = labels.getLabel(insnCtx.PARAM(0).toString());
        Label end = labels.getLabel(insnCtx.PARAM(1).toString());
        Label handler = labels.getLabel(insnCtx.PARAM(2).toString());
        String type = insnCtx.PARAM(3).toString().substring(1);

        methodVisitor.visitTryCatchBlock(start, end, handler, type);

        return null;
    }

    // ------------------------------------------- Fields, Variables, Frames -------------------------------
    @Override
    public Object visitFieldop(JavaByteGrammarParser.FieldopContext ctx){

        InstructionContext insnCtx = (InstructionContext) ctx.getParent().getParent();

        switch (insnCtx.opcode().start.getType()) {

            case FIELDACC:
                accessCalc(insnCtx);
                break;

            case FIELDVISIT:
                String desc = insnCtx.getChild(1).toString().substring(1);
                String name = insnCtx.getChild(2).toString().substring(1);
                String sign = null;
                if (insnCtx.getChild(3)!=null) sign = insnCtx.getChild(3).toString().substring(1);
                Object obj = null;
                fieldVisitor = classWriter.visitField(fieldAccess, name, desc, sign, obj);
                fieldVisitor.visitEnd();
                break;
        }

        return null;
    }

    @Override
    public Object visitLocalvariable(JavaByteGrammarParser.LocalvariableContext ctx){

        InstructionContext insnCtx = (InstructionContext) ctx.getParent().getParent();
        int off = (insnCtx.getChildCount() == 6) ? 0 : 1;

        String name = insnCtx.PARAM(0).toString().substring(1);
        String desc = insnCtx.PARAM(1).toString().substring(1);
        String signature = (off == 1) ? null : insnCtx.PARAM(2).toString().substring(1);
        Label start = labels.getLabel(insnCtx.PARAM(2+off).toString());
        Label end = labels.getLabel(insnCtx.PARAM(3+off).toString());
        int index = Integer.parseInt(insnCtx.PARAM(4+off).toString());

        methodVisitor.visitLocalVariable(name, desc, signature, start, end, index);

        return null;
    }

    @Override
    public Object visitFrame(JavaByteGrammarParser.FrameContext ctx){

        InstructionContext insnCtx = (InstructionContext) ctx.getParent().getParent();

        String type = insnCtx.PARAM(0).toString().substring(1);
        if (!type.startsWith("F_")) type = "F_" + type;
        int typecode = (int) reflector(type.toUpperCase());

        int nLocal = 0;
        Object[] local = null;
        int nStack = 0;
        Object[] stack = null;

        if (insnCtx.getChildCount() == 6) {
            nLocal = Integer.parseInt(insnCtx.PARAM(1).toString());
            local = variableTypeInterpreter(insnCtx.PARAM(2).toString(), labels);
            nStack = Integer.parseInt(insnCtx.PARAM(3).toString());
            stack = variableTypeInterpreter(insnCtx.PARAM(4).toString(), labels);
        }

        methodVisitor.visitFrame(typecode, nLocal, local, nStack, stack);

        return null;
    }

    @Override
    public Object visitMultianewarray(JavaByteGrammarParser.MultianewarrayContext ctx){

        InstructionContext insnCtx = (InstructionContext) ctx.getParent().getParent();

        String desc = insnCtx.PARAM(0).toString().substring(1);
        int dims = Integer.parseInt(insnCtx.PARAM(1).toString());

        methodVisitor.visitMultiANewArrayInsn(desc, dims);

        return null;
    }

    // ------------------------------------------ Annotations ---------------------------------------

    @Override
    public Object visitDefaultAnnotation(JavaByteGrammarParser.DefaultAnnotationContext ctx){

        AnnotationVisitor annotationVisitor = methodVisitor.visitAnnotationDefault();
        annotationVisitor.visitEnd();

        return null;
    }

    @Override
    public Object visitMethodAnnotation(JavaByteGrammarParser.MethodAnnotationContext ctx){

        InstructionContext insnCtx  = (InstructionContext) ctx.getParent().getParent();

        String desc = insnCtx.PARAM(0).toString();
        boolean visible = (insnCtx.PARAM(1).toString() == "0" || insnCtx.PARAM(1).toString() == "#false") ? true : false;

        AnnotationVisitor annotationVisitor = methodVisitor.visitAnnotation(desc, visible);
        annotationVisitor.visitEnd();

        return null;

    }

    @Override
    public Object visitTypeAnnotation(JavaByteGrammarParser.TypeAnnotationContext ctx){

        InstructionContext insnCtx  = (InstructionContext) ctx.getParent().getParent();
        int off = (insnCtx.getChildCount() == 4) ? 0 : 1;

        int typeRef = 0;
        try {
            typeRef = Integer.parseInt(insnCtx.PARAM(0).toString());
        } catch (NumberFormatException e) {
            //TODO: Reflect TypeReference from org.objectweb.asm.TypeReference
            // Maybe it is best to put Type, TryCatch and Insn Annotation in one function
            System.out.println("TypeAnnotation Annotation: Type Reference By Name not implemented.");
        }

        TypePath typePath = null;
        if (off == 1) typePath = TypePath.fromString(insnCtx.PARAM(1).toString());

        String desc = insnCtx.PARAM(1+off).toString();
        boolean visible = (insnCtx.PARAM(2+off).toString() == "0" || insnCtx.PARAM(2+off).toString() == "#false") ? true : false;

        AnnotationVisitor annotationVisitor = methodVisitor.visitTypeAnnotation(typeRef, typePath, desc, visible);
        annotationVisitor.visitEnd();

        return null;
    }

    @Override
    public Object visitParameterAnnotation(JavaByteGrammarParser.ParameterAnnotationContext ctx){

        InstructionContext insnCtx  = (InstructionContext) ctx.getParent().getParent();

        int parameter = Integer.parseInt(insnCtx.PARAM(0).toString());
        String desc = insnCtx.PARAM(1).toString();
        boolean visible = (insnCtx.PARAM(2).toString() == "0" || insnCtx.PARAM(2).toString() == "#false") ? true : false;

        AnnotationVisitor annotationVisitor = methodVisitor.visitParameterAnnotation(parameter, desc, visible);
        annotationVisitor.visitEnd();

        return null;
    }

    @Override
    public Object visitInsnAnnotation(JavaByteGrammarParser.InsnAnnotationContext ctx){

        InstructionContext insnCtx  = (InstructionContext) ctx.getParent().getParent();
        int off = (insnCtx.getChildCount() == 4) ? 0 : 1;

        int typeRef = 0;
        try {
            typeRef = Integer.parseInt(insnCtx.PARAM(0).toString());
        } catch (NumberFormatException e) {
            typeRef = (int) typeReflector(insnCtx.PARAM(0).toString().substring(1));
        }

        TypePath typePath = null;
        if (off == 1) typePath = TypePath.fromString(insnCtx.PARAM(1).toString());

        String desc = insnCtx.PARAM(1+off).toString();
        boolean visible = (insnCtx.PARAM(2+off).toString() == "0" || insnCtx.PARAM(2+off).toString() == "#false") ? true : false;

        AnnotationVisitor annotationVisitor = methodVisitor.visitInsnAnnotation(typeRef, typePath, desc, visible);
        annotationVisitor.visitEnd();

        return null;
    }

    @Override
    public Object visitTrycatchAnnotation(JavaByteGrammarParser.TrycatchAnnotationContext ctx){

        //TODO: Implement
        System.out.println("Try Catch Annotation not implemented.");

        return null;
    }

    @Override
    public Object visitLocalVariableAnnotation(JavaByteGrammarParser.LocalVariableAnnotationContext ctx){

        //TODO: Implement
        System.out.println("Local Variable Annotation not implemented.");

        return null;
    }

    // ------------------------------------------ Other Methods ----------------------------------------

    private void setDefault(ResetType r){
        switch (r) {
            case ALL:  setDefault(METHOD);
                       setDefault(FIELD);
            case CLASS: classacc = 0;
                        classname = null;
                        signature = null;
                        supername = "java/lang/Object";
                        interfaces = null;
                        break;
            case METHOD:methodAccess = 0;
                        methodName = null;
                        methodDesc = null;
                        methodSign = null;
                        methodExcep = null;
                        break;
            case FIELD: fieldAccess = 0;
        }
    }

    public byte[] getBinary(){
        return classWriter.toByteArray();
    }
}