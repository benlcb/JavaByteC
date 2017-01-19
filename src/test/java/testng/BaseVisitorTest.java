package testng;

import javabytec.assembler.AssemblerAPI;
import org.objectweb.asm.util.CheckClassAdapter;
import org.objectweb.asm.ClassReader;
import org.testng.Assert;

import java.io.PrintWriter;
import java.io.StringWriter;

import java.lang.reflect.Method;

public class BaseVisitorTest {

    private final String examplesPath = "/home/cajus/JavaByteC/examples/";
    private final String testClassName = "Demo";

    @org.testng.annotations.BeforeMethod
    public void setUp() throws Exception {

    }

    @org.testng.annotations.AfterMethod
    public void tearDown() throws Exception {

    }

    @org.testng.annotations.Test
    public void testBasicClass() throws Exception {

        String codeFileName = "code.demo.asm";

        byte[] classBytecode =
                AssemblerAPI.fileToBytecode(examplesPath + codeFileName);


        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        CheckClassAdapter.verify(new ClassReader(classBytecode), false, pw);

        Assert.assertEquals(sw.toString().length(), 0);

    }

    @org.testng.annotations.Test
    public void testTryCatchBlock() throws Exception {

        String codeFileName = "trycatchloadable.code.demo.asm";

        byte[] classBytecode =
                AssemblerAPI.fileToBytecode(examplesPath + codeFileName);


        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        CheckClassAdapter.verify(new ClassReader(classBytecode), false, pw);

        Assert.assertEquals(sw.toString().length(), 0);

    }

    @org.testng.annotations.Test
    public void testTest() throws Exception {

        String codeFileName = "invalid.code.demo.asm";

        byte[] classBytecode =
                AssemblerAPI.fileToBytecode(examplesPath + codeFileName);


        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        CheckClassAdapter.verify(new ClassReader(classBytecode), false, pw);

        Assert.assertNotEquals(sw.toString().length(), 0);

    }

    @org.testng.annotations.Test
    public void testLoadTryCatch() throws Exception {

        String codeFileName = "trycatchloadable.code.demo.asm";

        byte[] classBytecode =
                AssemblerAPI.fileToBytecode(examplesPath + codeFileName);

        ClassLoader parentClassLoader = TestClassLoader.class.getClassLoader();
        TestClassLoader testClassLoader = new TestClassLoader(parentClassLoader);

        Class demoClass = testClassLoader.loadByteArray(testClassName, classBytecode);
        Object testClassInstance = demoClass.newInstance();

        Method methodUnderTest = testClassInstance.getClass().getMethod("test", int.class);
        int result = (int) methodUnderTest.invoke(null, 20);

        Assert.assertEquals(result, -1);
    }

}