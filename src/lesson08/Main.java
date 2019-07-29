package lesson08;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.util.Scanner;
import java.util.concurrent.locks.LockSupport;

public class Main {
    public static void main(String[] args) throws Exception {
        Worker worker = new SomeClass();
        worker.doWork();
        String workerStart = "public class SomeClass implements Worker {\n" +
                "    public SomeClass(){\n" +
                "    }\n" +
                "\n" +
                "    public void doWork(){";

        String workerEnd = "    }\n" +
                "    static { \n" +
                "        System.out.println(\"Иван\");\n" +
                "    }\n" +
                "}";
        String givenCode = linesCodeReader();
        String givenClass = workerStart + givenCode + workerEnd;
        codeStringWriter(givenClass);
        givenFileCompiler();
        while (true) {
            LockSupport.parkNanos(5_000_000_000L);
            useCustomClassLoader();
        }
    }

    private static String linesCodeReader() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите код метода doWork: ");
        String givenInput = "";
        String givenMethod = "      ";
        while (!(givenInput = scanner.nextLine()).equals("")) {
            givenMethod = givenMethod + givenInput + "\n";
        }
        System.out.println("Код метода doWork: " + "\n" + givenMethod);
        return givenMethod;
    }

    public static void codeStringWriter(String givenStringCode) throws IOException {
        String str = givenStringCode;
        FileOutputStream outputStream = new FileOutputStream("file.txt");
        byte[] strToBytes = str.getBytes();
        outputStream.write(strToBytes);
        outputStream.close();
    }

    public static void givenFileCompiler() throws IOException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("file.txt"));
            PrintWriter printWriter = new PrintWriter("SomeClass.java");
            String reader = "";
            System.out.println("start");
            while ((reader = (bufferedReader.readLine())) != null) {
                printWriter.write(reader);
            }
            bufferedReader.close();
            printWriter.close();
            JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
            System.out.println(javaCompiler);
            javaCompiler.run(null, null, null, "SomeClass.java");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void useCustomClassLoader() throws Exception {
        ClassLoader customClassloader = new CustomClassloader();
        Class<?> loadingWorker = customClassloader.loadClass("SomeClass");
        Worker loadingInterfaceWorker = (Worker) loadingWorker.newInstance();
        loadingInterfaceWorker.doWork();
    }
}