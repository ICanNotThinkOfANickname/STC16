package lesson05.Tasks2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("vocabulary.txt");
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), UTF_8));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        List<String> initWords = new ArrayList<>();
        String inputFirst;
        for (int i = 0; i < 1000; i++) {
            inputFirst = bufferedReader.readLine();
            initWords.add(inputFirst);
        }
        bufferedReader.close();
        System.out.println("Размерность массива " + initWords.size() + ": " + initWords);


        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), UTF_8));
        } catch (
                FileNotFoundException ex) {
            ex.printStackTrace();
        }

        List<String> words = new ArrayList<>();
        String inputSecond;
        while ((inputSecond = bufferedReader.readLine()) != null) {
            words.add(inputSecond);
        }
        bufferedReader.close();
        System.out.println("Размерность массива " + words.size() + ": " + words + "\r\n");


        GenerateClass generateClass = new GenerateClass(initWords, words);
        generateClass.generateParagraph(15, 20, 0.7);

        String[] array = words.stream().toArray(String[]::new);
        generateClass.getFiles("C:\\", 3, 1000, 2, array);
    }
}