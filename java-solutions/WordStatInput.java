import java.io.*;
import java.util.*;

public class WordStatInput {
    public static String[] readFile(String filename) throws IOException,
                                                     FileNotFoundException,
                                                      UnsupportedEncodingException
    {
        MyScanner sc = new MyScanner(new BufferedReader(
                                        new InputStreamReader(
                                            new FileInputStream(filename),
                                            "UTF8")));
        String[] words = new String[1];
        int ptr = 0;
        try {
            while (sc.hasNextToken()) {
                String word = sc.nextToken();
                if (ptr >= words.length) {
                    words = Arrays.copyOf(words, (words.length * 3 + 1) / 2);
                }
                words[ptr++] = word;
            }
        }
        finally {
            sc.close();
        }
        return Arrays.copyOf(words, ptr);
    }

    public static void main(String[] args) {
        String inputFilename = args[0];
        String outputFilename = args[1];
        HashMap<String, Integer> counter = new HashMap<String, Integer>();
        HashMap<Integer, String> index = new HashMap<Integer, String>();
        int currentIndex = 0;
        try {
            String[] words = readFile(inputFilename);
            for (String word : words) {
                // System.out.println(word);
                String currentWord = word.toLowerCase();
                if (currentWord.length() > 0) {
                    if (!counter.containsKey(currentWord)) {
                        index.put(currentIndex++, currentWord);
                    }
                    counter.put(currentWord, (Integer)counter.getOrDefault(currentWord, 0) + 1);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No such file: " + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            System.out.println("UnsupportedEncoding: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }

        try {
            //System.out.println("writer");
            PrintWriter writer = new PrintWriter(
                new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(outputFilename),
                "UTF8"
            )));
            try {
                for (int ind = 0; ind < currentIndex; ind++) {
                    String word = index.get(ind);
                    // System.out.println(word + " " + counter.get(word));
                    writer.println(word + " " + counter.get(word));
                }
            } finally {
                writer.close();
            }
        } catch(IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}