package testng;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/*
ClassLoader that provides the ability to load a byte array or a class file.
 */
public class TestClassLoader extends ClassLoader {

    public TestClassLoader(ClassLoader parent) {
        super(parent);
    }

    public Class loadTestClass(String fileName, String className, String examplesFolderPath) throws Exception {

        //if(!"reflection.MyObject".equals(name)) return super.loadClass(name);
        String url = "file:" + examplesFolderPath + fileName + ".class";
        URL myUrl = new URL(url);
        URLConnection connection = myUrl.openConnection();
        InputStream input = connection.getInputStream();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int data = input.read();

        while(data != -1){
            buffer.write(data);
            data = input.read();
        }

        input.close();

        byte[] classData = buffer.toByteArray();

        return defineClass(className,
                classData, 0, classData.length);

    }

    public Class loadByteArray(String className, byte[] classData){
        return defineClass(className,classData, 0, classData.length);
    }
}
