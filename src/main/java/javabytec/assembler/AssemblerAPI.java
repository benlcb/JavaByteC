package javabytec.assembler;

import javabytec.parser.JavaByteGrammarLexer;
import javabytec.parser.JavaByteGrammarParser;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by cajus on 23/11/16.
 */
public class AssemblerAPI {

    public static byte[] stringToBytecode(String inputString) throws IOException {

        ANTLRInputStream input = new ANTLRInputStream(inputString);
        JavaByteGrammarLexer lexer = new JavaByteGrammarLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JavaByteGrammarParser parser = new JavaByteGrammarParser(tokens);

        ParseTree tree = parser.code();
        BaseVisitor bv = new BaseVisitor();
        bv.visit(tree);

        return bv.getBinary();

    }

    public static void stringToBytecodeFile(String inputString, String outputPath){

        if (!outputPath.endsWith(".class")) outputPath += ".class";

        try {
            File f = new File(outputPath);
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(stringToBytecode(inputString));
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static byte[] fileToBytecode(String inputPath) throws IOException{

        ANTLRInputStream input = new ANTLRFileStream(inputPath);
        JavaByteGrammarLexer lexer = new JavaByteGrammarLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JavaByteGrammarParser parser = new JavaByteGrammarParser(tokens);

        ParseTree tree = parser.code();
        BaseVisitor bv = new BaseVisitor();
        bv.visit(tree);

        return bv.getBinary();

    }

    public static void main(String[] args) throws Exception {

        String inputPath = "./demo.code";
        String outputPath = "./Demo.class";
        Charset encoding = Charset.defaultCharset();

        if (args.length == 2) {

            inputPath = args[0];
            outputPath = args[1];

        } else if (args.length == 3){

            inputPath = args[0];
            outputPath = args[1];
            // TODO: Implement Universal reflector to get charset
            encoding = Charset.defaultCharset();

        } else {
            System.out.println("API: AssemblerAPI inputPath outputPath \n" +
                                "Example: AssemblerAPI ./demofolder/demo.code ./demofolder/Demo.class");
            return;
        }

        byte[] inputByteArray = Files.readAllBytes(Paths.get(inputPath));
        String inputString =  new String(inputByteArray, encoding);

        stringToBytecodeFile(inputString, outputPath);
    }

}
