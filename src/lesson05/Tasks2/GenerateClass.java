package lesson05.Tasks2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GenerateClass {
    List<String> words;
    List<String> initWords;
    Random random = new Random();
    String prevOffer = "";
    String paragraph = "";
    List<String> stringArrayList = new ArrayList<>(Arrays.asList(", ", " ", " "));
    String endOfOffer = "(.|!|?)+\"\"";
    String alphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЬЭЮЯ";
    List<String> prevOfferWord = new ArrayList<>();
    ArrayList<FileOutputStream> arrayList = new ArrayList<>();

    public GenerateClass(List<String> initWords, List<String> words) {
        this.initWords = initWords;
        this.words = words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    /* Метод генерирования предложения */
    public void generateOffer(int n) {
        String word;
        prevOffer = "";
        prevOfferWord.clear();
        prevOffer = prevOffer + alphabet.charAt(random.nextInt(alphabet.length())) + " ";
        for (int i = 0; i < n - 1; i++) {
            word = initWords.get(random.nextInt(initWords.size()));
            prevOffer = prevOffer + word;
            prevOffer = prevOffer + stringArrayList.get(random.nextInt(stringArrayList.size()));
            prevOfferWord.add(word);
        }
        word = words.get(random.nextInt(words.size()));
        prevOffer = prevOffer + words.get(random.nextInt(words.size()));
        prevOffer = prevOffer + endOfOffer.charAt(random.nextInt(endOfOffer.length()));
        prevOfferWord.add(word);
    }

    /* Генерирования абзацев */
    public void generateParagraph(int n1, int n2, double givenProb) {
        for (int i = 0; i < n2; i++) {
            generateOffer(n1);
            paragraph = paragraph + prevOffer;
            paragraph = paragraph + " ";
            if (probabilityGenerator(givenProb)) {
                String x = words.get(random.nextInt(words.size()));
                generateOffer(n1);
                String newSentence = prevOffer.substring(0, prevOffer.length() - 2) + " " + x + endOfOffer.charAt(random.nextInt(endOfOffer.length())) + " ";
                paragraph = paragraph + newSentence;
                i++;
            }
        }
        paragraph = paragraph + "\r\n";
    }

    public boolean probabilityGenerator(double probability) {
        double random = this.random.nextDouble();
        if (random < probability) {
            return true;
        } else {
            return false;
        }
    }

    public void getFiles(String path, int n, int size, int probability, String[] words) {
        setWords(Arrays.asList(words));
        for (int i = 0; i < n; i++) {
            byte[] buffer;
            int x = 0;
            FileOutputStream y = serialisation(path, i);
            while (x < size) {
                generateParagraph(15, 20, (double) probability / 100);
                buffer = paragraph.getBytes();
                x = buffer.length;
                try {
                    y.write(buffer, 0, size);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                paragraph = "";
                generateParagraph(15, 20, (double) probability / 100);
            }
        }
    }

    /*Сохранение файлов*/
    public FileOutputStream serialisation(String path, int i) {
        try {
            arrayList.add(new FileOutputStream(path + (i + 1) + ".txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return arrayList.get(i);
    }
}