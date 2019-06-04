package lesson05.Tasks2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static java.util.Arrays.asList;

public class GenerateClass {
    List<String> words = new ArrayList<>();
    List<String> initWords = new ArrayList<>();
    Random random = new Random();
    String prevOffer = "";
    String paragraph = "";
    String endOfOffer = "(.|!|?)+\"\"";
    List<String> comma = new ArrayList<>(asList(", ", " ", " "));
    String alphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЬЭЮЯ";
    List<String> prevOfferWord = new ArrayList<>();

    public GenerateClass(List<String> words, List<String> initWords) {
        this.words = words;
        this.initWords = initWords;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    /*Генерация предложений*/
    public void generateOffer(int n) {
        String words = "";
        prevOffer = "";
        prevOfferWord.clear();
        prevOffer = prevOffer + alphabet.charAt(random.nextInt(alphabet.length())) + " ";
        for (int i = 0; i < n - 1; i++) {
            words = initWords.get(random.nextInt(this.words.size()));
            prevOffer = prevOffer + words;
            prevOffer = prevOffer + comma.get(random.nextInt(comma.size()));
            prevOfferWord.add(words);
        }
        prevOffer = prevOffer + this.words.get(random.nextInt(this.words.size()));
        prevOffer = prevOffer + endOfOffer + endOfOffer.charAt(random.nextInt(endOfOffer.length()));
        prevOfferWord.add(words);
    }

    /*Генерация абзацев*/
    public void generateParagraph(int n1, int n2, double probability) {
        for (int i = 0; i < n2; i++) {
            generateOffer(n1);
            paragraph = paragraph + prevOffer;
            paragraph = paragraph + " ";
            if (probabilityGenerator(probability)) {
                String word = words.get(random.nextInt(words.size()));
                generateOffer(n1);
                String newOffer = prevOffer.substring(0, prevOffer.length() - 2) + " " + endOfOffer.charAt(random.nextInt(endOfOffer.length())) + " ";
                paragraph = paragraph + newOffer;
                i++;
            }
        }
        paragraph = paragraph + "\r\n";
    }

    /*Генерация условий*/
    public void getFiles(String path, int n, int size, int probability, String[] words) {
        setWords(Arrays.asList(words));
        for (int i = 0; i < n; i++) {
            byte[] buffer = new byte[0];
            int x = 0;
            int y = 0;
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(path + (x + 1) + ".txt");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            while (y < size) {
                generateParagraph(15, 20, probability / 100);
                buffer = paragraph.getBytes();
                y = buffer.length;
            }
        }
    }

    /*Генерация probability, вхождения одного из слов массива в следующее предложение*/
    public boolean probabilityGenerator(double probability) {
        double random = this.random.nextDouble();
        if (random < probability) {
            return true;
        } else {
            return false;
        }
    }
}
