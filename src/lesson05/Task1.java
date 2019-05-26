package lesson05;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Task1 {
    public static void main(String[] args) {
        try {
            Set<String> list = new HashSet<>();
            File file = new File("note");
            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), UTF_8));
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            String line = null;
            while (true) {
                try {
                    if ((line = bufferedReader.readLine()) == null) break;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                String[] tokens = line.toLowerCase().split("[ .,?!:;–-]+");

                for (String token : tokens) {
                    list.add(token);
                }
            }
            TreeSet<String> sortedSet = new TreeSet<>();
            sortedSet.addAll(list);
            Iterator<String> iterator = sortedSet.iterator();
            while (iterator.hasNext()) {
                Writer writer = new FileWriter("newnote.txt");
                for (String string : sortedSet) {
                    writer.write(string);
                    writer.write(System.getProperty("line.separator"));
                }
                writer.flush();
                System.out.println(iterator.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

