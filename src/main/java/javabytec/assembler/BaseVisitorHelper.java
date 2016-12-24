package javabytec.assembler;

import javabytec.parser.JavaByteGrammarParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.lang.reflect.Field;

/**
 * Created by cajus on 10/11/16.
 */
public class BaseVisitorHelper {


    //TODO: Generalise Reflectors

     public static int reflector(String s){

        OpcodeReflection opcr = new OpcodeReflection();
        Field opcField;
        int opcVal;
        Class opcodesClass = opcr.getClass();
        try {
            opcField = opcodesClass.getField(s);
            opcVal = opcField.getInt(opcr);
        } catch (NoSuchFieldException e) {
            System.out.println("Relfection Failed: Opcode Name [" + s + "] does not exist in objectweb.asm.Opcodes");
            e.printStackTrace();
            return 0;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return 0;
        }

        return opcVal;
    }

    static Integer integerReflector(String s){

        OpcodeReflection opcr = new OpcodeReflection();
        Field opcField;
        Integer opcVal;
        Class opcodesClass = opcr.getClass();
        try {
            opcField = opcodesClass.getField(s);
            opcVal = (Integer) opcField.get(opcr);
        } catch (NoSuchFieldException e) {
            System.out.println("Relfection Failed: Opcode Name [" + s + "] does not exist in objectweb.asm.Opcodes");
            e.printStackTrace();
            return 0;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return 0;
        }

        return opcVal;
    }

    public static int accessCalc(ParserRuleContext ctx){
        return accessCalc(ctx, 1);
    }

    public static int accessCalc(ParserRuleContext ctx, int index){
        int accumulate = 0;

        for (;ctx.getChild(index)!=null; index++){
            accumulate += reflector(ctx.getChild(index).toString().substring(1));
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
                local[i] = integerReflector(arrayed[i].substring(1).toUpperCase());
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
