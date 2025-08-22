package org.delonce;

import javassist.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class MyTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classFileBuffer) {

        var byteCode = classFileBuffer;
        String instrumentedClassName = "sun.net.www.protocol.http.HttpURLConnection";
        //System.out.println(className);
        var name = instrumentedClassName.replaceAll("\\.", "/");
        if (!className.equals(name)) {
            return byteCode;
        }

        System.out.println("Transforming the class: " + className);
        try {
            ClassPool cp = ClassPool.getDefault();
            cp.appendClassPath(new LoaderClassPath(loader));

            System.out.println("Getting class {}" + instrumentedClassName);
            CtClass cc = cp.get(instrumentedClassName);

            System.out.println("methods:");
            for (CtMethod declaredMethod : cc.getDeclaredMethods()) {
                System.out.println(declaredMethod.getName());
            }

            for (CtConstructor ctMethod : cc.getConstructors()) {
                ctMethod.insertAfter("System.out.println(\"Local variable value: \" + this.getURL().toString());");
                ctMethod.insertAfter("System.out.println(\"Local variable value: \" + this.getHeaderFields());");
                ctMethod.insertAfter("System.out.println(\"Local variable value: \" + this.getMethod());");
                ctMethod.insertAfter("""
                        java.net.URL myTestUrl = this.getURL();
                        System.out.println("HOST AND PORT: " + this.getHostAndPort(myTestUrl));
                        """);
            }



            //CtMethod m = cc.getDeclaredMethod(instrumentedMethodName);
            //m.insertAfter("""
            //                      System.out.println("Local variable value: " + $1.toString());
            //                      """
            //        .strip());

            byteCode = cc.toBytecode();
            cc.detach();

            saveToFile(instrumentedClassName, byteCode);

        } catch (NotFoundException | CannotCompileException | IOException e) {
            System.out.println("Exception: " + e);
        }
        return byteCode;
    }

    private static void saveToFile(String className, byte[] newClassByteCode) throws IOException {
        String[] split = className.split("\\.");
        String last = split[split.length - 1];
        try (FileOutputStream out = new FileOutputStream(last + ".class")) {
            out.write(newClassByteCode);
            System.out.println("Transformed class saved to file: " + last);
        }
    }
}