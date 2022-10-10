import java.util.*;
import java.io.*;

public class Wspp {
	public static char[] readFile(String filename) throws IOException,
													 FileNotFoundException,
													  UnsupportedEncodingException
	{
		char[] file = new char[128];
		int ptr = 0;
		BufferedReader reader = new BufferedReader(
			new InputStreamReader(
			new FileInputStream(filename),
			"UTF8"
		));
		char[] buffer = new char[128];
		int read = reader.read(buffer);
		while (read >= 0) {
			for (int i = 0; i < read; i++) {
				char c = buffer[i];
				if (ptr >= file.length) {
					file = Arrays.copyOf(file, (file.length * 3 + 1) / 2);
				}
				file[ptr++] = c;
			}
			read = reader.read(buffer);
		}
		reader.close();
		return file;
	}

	public static void main(String[] args) {
		String inputFilename = args[0];
		String outputFilename = args[1];
		HashMap<String, IntList> counter = new HashMap<>();
		HashMap<Integer, String> index = new HashMap<>();
		int currentIndex = 0;
		try {
			char[] file = readFile(inputFilename);
			StringBuilder word = new StringBuilder();
			int wordIndex = 1;
			for (int i = 0; i < file.length; i++) {
				char currentChar = file[i];
				boolean ok = ((Character.isLetter(currentChar) ||
					Character.getType(currentChar) == Character.DASH_PUNCTUATION ||
					currentChar == '\'') &&
					 (i + 1 != file.length));
				if (ok) {
					word.append(currentChar);
				}
				else {
					String currentWord = word.toString().toLowerCase();
					if (currentWord.length() > 0) {
						if (counter.containsKey(currentWord)) {
							counter.get(currentWord).append(wordIndex++);
						}
						else {
							counter.put(currentWord, new IntList(new int[]{wordIndex++}));
							index.put(currentIndex++, currentWord);
						}
					}
					word = new StringBuilder();
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
			PrintWriter writer = new PrintWriter(
				new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(outputFilename),
				"UTF8"
			)));
			try {
				for (int ind = 0; ind < currentIndex; ind++) {
					String word = index.get(ind);
					IntList occurences = counter.get(word);
					int size = occurences.size();
					writer.print(word + " " + size + " " + occurences.toString());
					writer.println();
				}
			} finally {
				writer.close();
			}
		} catch(IOException e) {
			System.out.println("IOException: " + e.getMessage());
		}
		
	}
}