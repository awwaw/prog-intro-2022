import java.io.*;
import java.util.*;

public class WordStatWordsShingles {
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
		Map<String, Integer> counter = new TreeMap<>();
		try {
			String[] words = readFile(inputFilename);
			for (String word : words) {
				String currentWord = word.toString().toLowerCase();
				if (currentWord.length() > 0) {
					int sub = 2;
					int ln = 3;
					if (currentWord.length() < 3) {
						sub = currentWord.length() - 1;
						ln = currentWord.length();
					}
					for (int j = 0; j < currentWord.length() - sub; j++) {
						int left = j;
						int right = left + ln;
						// System.out.println(left + " " + right);
						String shingle = currentWord.substring(left, right);
						counter.put(shingle, (Integer)counter.getOrDefault(shingle, 0) + 1);
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("No such file: " + e.getMessage());
		} catch (UnsupportedEncodingException e) {
			System.out.println("UnsupportedEncoding: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Bruh");
			e.printStackTrace();
		}

		try {
			PrintWriter writer = new PrintWriter(
				new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(outputFilename),
				"UTF8"
			)));
			try {
				for (Map.Entry<String, Integer> entry : counter.entrySet()) {
					String key = entry.getKey();
					Integer value = entry.getValue();
					writer.println(key + " " + value);
				}
			} finally {
				writer.close();
			}
		} catch(IOException e) {
			System.out.println("Bruh");
			e.printStackTrace();
		}
	}
}