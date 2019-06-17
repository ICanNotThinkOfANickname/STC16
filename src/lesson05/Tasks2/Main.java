package lesson05.Tasks2;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Main {
    public static void main(String[] args) throws IOException {
        /* Создание массива слов initWords размерностью <=1000 для генерирования предложений */
        File file = new File("vocabulary.txt");
        BufferedReader bufferedReaderFirst = new BufferedReader(new InputStreamReader(new FileInputStream(file), UTF_8));
        List<String> initWords = new ArrayList<>();
        String inputString;
        for (int i = 0; i < 1000; i++) {
            inputString = bufferedReaderFirst.readLine();
            initWords.add(inputString);
        }
        bufferedReaderFirst.close();
        System.out.println("Массив размерностью " + initWords.size() + ": " + initWords);

        /* Создание массива слов words размерностью <=1000 для реализации вероятности */
        BufferedReader bufferedReaderSecond = new BufferedReader(new InputStreamReader(new FileInputStream(file), UTF_8));
        List<String> words = new ArrayList<>();
        String inputLineSecond;
        while ((inputLineSecond = bufferedReaderSecond.readLine()) != null) {
            words.add(inputLineSecond);
        }
        bufferedReaderSecond.close();
        System.out.println("Массив слов words размерностью " + words.size() + ": " + words + "\r\n");
        GenerateClass generateClass = new GenerateClass(initWords, words);

        generateClass.generateParagraph(15, 20, 0.9);

        /* Генерация файлов */
        String[] array = words.stream().toArray(String[]::new);
        generateClass.getFiles("C:\\", 5, 5120, 50, array);
    }
}