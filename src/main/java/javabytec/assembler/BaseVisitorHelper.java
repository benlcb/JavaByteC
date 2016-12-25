package javabytec.assembler;

import org.antlr.v4.runtime.ParserRuleContext;
import org.objectweb.asm.TypeReference;
import java.lang.reflect.Field;

public class BaseVisitorHelper {
    /*
    The implementation of Opcodes which is needed for Refelction
     */
    static class OpcodeReflection implements org.objectweb.asm.Opcodes {}

    //TODO: Generalise Reflectors
    /*
    The reflectors used to convert Instruction Codes to Opcodes and similar
     */
     public static Object reflector(String fieldName){

        OpcodeReflection opcr = new OpcodeReflection();
        Field opcField;
        Object opcVal;
        Class opcodesClass = opcr.getClass();
        try {
            opcField = opcodesClass.getField(fieldName);
            opcVal = opcField.get(opcr);
        } catch (NoSuchFieldException e) {
            System.out.println("Relfection Failed: Opcode Name [" + fieldName + "] does not exist in objectweb.asm.Opcodes");
            e.printStackTrace();
            return 0;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return 0;
        }

        return opcVal;
    }

    public static Object typeReflector(String fieldName){

        TypeReference opcr = new TypeReference(0);
        Field opcField;
        Object opcVal;
        Class opcodesClass = opcr.getClass();
        try {
            opcField = opcodesClass.getField(fieldName);
            opcVal = opcField.get(opcr);
        } catch (NoSuchFieldException e) {
            System.out.println("Relfection Failed: TypeReference Name [" + fieldName + "] does not exist in objectweb.asm.Opcodes");
            e.printStackTrace();
            return 0;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return 0;
        }

        return opcVal;
    }

    /*
    Used to accumulate Access Flags in .methodAccess
     */
    public static int accessCalc(ParserRuleContext ctx){
        return accessCalc(ctx, 1);
    }

    public static int accessCalc(ParserRuleContext ctx, int index){
        int accumulate = 0;

        for (;ctx.getChild(index)!=null; index++){
            try {
                accumulate += Integer.parseInt(ctx.getChild(index).toString().substring(1));
            } catch (NumberFormatException e) {
                accumulate += (int) reflector(ctx.getChild(index).toString().substring(1));
            }
        }

        return accumulate;
    }


    /* variableTypeInterpreter:
    *  @PARAM: String s: String of Variable Types wraped in Array format
    *  @RETURN: Object[] local: Contains Strings and Ints representing Variable Types
    *  Needed for visitFrame in BaseVisitor.
    *  Example String: "{.INTEGER .NULL Ljava/io/PrintStream; #L1 #L2}"
    */
    static Object[] variableTypeInterpreter(String s, LabelMap labelMap){

        String[] arrayed = s.replaceAll("[{}]","")
                            .split(" ");

        Object[] local = new Object[arrayed.length];

        for (int i = 0; i<local.length; i++) {

            if (arrayed[i].startsWith(".")){
                local[i] = (Integer) reflector(arrayed[i].substring(1).toUpperCase());
            } else if (arrayed[i].startsWith("#")) {
                local[i] = labelMap.getLabel(arrayed[i]);
            } else {
                local[i] = arrayed[i];
            }

        }

        return local;

    }

    static int[] intArrayInterpreter(String array){

        String[] arrayed = array.replaceAll("[{}]","")
                                .split(" ");

        int[] ints = new int[arrayed.length];

        for (int i = 0; i<ints.length; i++){
            ints[i] = Integer.parseInt(arrayed[i]);
        }

        return ints;
    }


}
