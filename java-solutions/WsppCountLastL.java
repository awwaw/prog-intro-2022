import java.util.*;
import java.io.*;

public class WsppCountLastL {
	public static void main(String[] args) {
		String inputFilename = args[0];
		String outputFilename = args[1];
		Map<String, Integer> occurences = new HashMap<>(); 
		Map<String, IntList> indexes = new HashMap<>(); 
		Map<String, Integer> firstIndex = new HashMap<>(); 
		Set<String> allWords = new HashSet<>();
		int currentIndex = 0;
		try {
			MyScanner sc = new MyScanner(new BufferedReader(
										new InputStreamReader(
											new FileInputStream(inputFilename),
											"UTF8")));
			try {
				while (sc.hasNextLine()) {
					Map<String, Integer> lineIndex = new HashMap<>();
					String line = sc.nextLine();
					StringBuilder word = new StringBuilder();
					int wordIndex = 1;
					Set<String> wordsInLine = new HashSet<>();
					for (int i = 0; i < line.length(); i++) {
						char currentChar = line.charAt(i);
						boolean ok = ((Character.isLetter(currentChar) ||
							Character.getType(currentChar) == Character.DASH_PUNCTUATION ||
							currentChar == '\''));
						if (ok) {
							word.append(currentChar);
						}
						if (!ok || i + 1 == line.length()) {
							String currentWord = word.toString().toLowerCase();
							if (currentWord.length() > 0) {
								if (!firstIndex.containsKey(currentWord)) {
									firstIndex.put(currentWord, currentIndex);
								}
								currentIndex++;
								wordsInLine.add(currentWord);
								allWords.add(currentWord);
								occurences.put(currentWord, (Integer)occurences.getOrDefault(currentWord, 0) + 1);
								lineIndex.put(currentWord, Math.max((Integer)lineIndex.getOrDefault(currentWord, 0), wordIndex++));
							}
							word = new StringBuilder();
						}
					}
					for (String w : wordsInLine) {
						if (indexes.containsKey(w)) {
							indexes.get(w).append((int)lineIndex.get(w));
						}
						else {
							indexes.put(w, new IntList(new int[]{lineIndex.get(w)}));
						}
					}
				}
			}
			finally {
				sc.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println("No such file: " + e.getMessage());
		} catch (UnsupportedEncodingException e) {
			System.out.println("UnsupportedEncoding: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
		}

		List<String> order = new ArrayList<>();
		for (String word : allWords) {
			order.add(word);
		}

		Collections.sort(order, new Comparator<String>() {
			public int compare(String a, String b) {
				int o1 = occurences.get(a);
				int o2 = occurences.get(b);
				if (o1 < o2) {
					return -1;
				}
				else if (o1 > o2) {
					return 1;
				}
				else {
					int f1 = firstIndex.get(a);
					int f2 = firstIndex.get(b);
					if (f1 < f2) {
						return -1;
					}
					else if (f1 > f2) {
						return 1;
					}
					else {
						return 0;
					}
				}
			}
		});

		try {
			PrintWriter writer = new PrintWriter(
				new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(outputFilename),
				"UTF8"
			)));
			try {
				// for (int ind = 0; ind < currentIndex; ind++) {
				// 	String word = index.get(ind);
				// 	IntList occurences = counter.get(word);
				// 	int size = occurences.size();
				// 	writer.print(word + " " + size + " " + occurences.toString());
				// 	writer.println();
				// }
				for (String word : order) {
					writer.println(word + " " + occurences.get(word) + " " + indexes.get(word).toString());
				}
			} finally {
				writer.close();
			}
		} catch(IOException e) {
			System.out.println("IOException: " + e.getMessage());
		}
		
	}
}