import java.util.*;
import java.io.*;

public class MyScanner {
	private Reader reader;
	private static final int bufferMaxSize = 256;
	private int bufferSize = 0;
	private char[] buffer = new char[this.bufferMaxSize];
	private int index = -1;
	private boolean closed = false;

	public MyScanner(Reader reader) {
		this.reader = reader;
	}

	private int updateBuffer() throws IOException, IllegalStateException { // read buffer and return it's size if success, otherwise -1
		if (closed) {
			throw new IllegalStateException("Scanner is already closed");
		}
		int read = reader.read(buffer);
		bufferSize = read;
		return read;
	}

	private boolean hasNextChar() throws IOException, IllegalStateException { // return true if there is a char left
		if (bufferSize == -1) {
			return false;
		}
		if (index == bufferSize - 1) {
			if (this.updateBuffer() > 0) {
				index = -1;
				return true;
			}
			else {
				return false;
			}
		}
		return true;
	}

	public boolean hasNextToken() throws IOException, IllegalStateException {
		return this.hasNextChar();
	}

	public boolean hasNextLine() throws IOException, IllegalStateException {
		return this.hasNextChar();
	}

	private static boolean checkForWord(char c) {
		return (Character.isLetter(c) ||
		 Character.getType(c) == Character.DASH_PUNCTUATION ||
		  c == '\'');
	}

	private static boolean checkForNumber(char c) {
		return (Character.isDigit(c) ||
                ((int)'a' <= (int)c && (int)c <= (int)'j') ||
                (c == '-' || c == '+') ||
                (c == 'o' || c == 'O'));
	}

	private static char convert(char c) {
		return (char)((int)c - (int)'a' + (int)'0');
	}

	private String next(int type) throws IOException, IllegalStateException, NoSuchElementException { // 0 - word, 1 - number
		StringBuilder sb = new StringBuilder();
		if (!this.hasNextChar()) {
			throw new NoSuchElementException("Can't read more tokens");
		}
		while (this.hasNextChar()) {
			index++;
			char c = buffer[index];
			
			boolean ok = true;
			if (type == 0) {
				ok &= this.checkForWord(c);
			}
			if (type == 1) {
				ok &= this.checkForNumber(c);
			}

			// System.out.println("[" + c + " " + index + " " + ok + "]");
			// System.out.println(Character.isDigit(c) + " " + ((int)'a' <= (int)c && (int)c <= (int)'j') + " " + (c == '-' || c == '+') + " " + (c == 'o' || c == 'O'));
			// System.out.println("---------------------");

			if (ok) {
				if (type == 1) {
					if ((int)'a' <= (int)c && (int)c <= (int)'j') {
						c = convert(c);
					}
				}
				sb.append(c);
			}
			else {
				// System.out.println("{" + sb.toString() + "}");
				if (sb.length() > 0) {
					return sb.toString();
				}
				sb = new StringBuilder();
			}
		}
		// System.out.println("{" + sb.toString() + "}");
		return sb.toString();
	}

	public String nextToken() throws IOException, IllegalStateException, NoSuchElementException {
		// System.out.println("________________________________");
		return this.next(0);
	}

	public int nextInt() throws NumberFormatException, IOException, IllegalStateException, NoSuchElementException {
		String candidate = this.next(1).toLowerCase();
		int radix = 10;
		if (candidate.length() > 0 && candidate.charAt(candidate.length() - 1) == 'o') {
			candidate = candidate.substring(0, candidate.length() - 1);
			radix = 8;
		}
		if (candidate.charAt(0) == '-') {
			return Integer.parseInt(candidate, radix);
		}
		return Integer.parseUnsignedInt(candidate, radix);
	}

	private boolean isNextLine() throws IllegalStateException, IOException, NoSuchElementException {
		if (buffer[index] == '\r') {
			if (this.hasNextChar()) {
				// index++;
				if (buffer[index + 1] == '\n') {
					index++;
					return true;
				}
			}
			return true;
		}
		else if (buffer[index] == '\n' ||
					buffer[index] == '\u2028' ||
					buffer[index] == '\u2029' ||
					buffer[index] == '\u0085') {
			return true;
		}
		return false;
	}

	public String nextLine() throws IllegalStateException, IOException, NoSuchElementException {
		StringBuilder line = new StringBuilder();
		while (this.hasNextChar()) {
			index++;
			if (this.isNextLine()) {
				return line.toString();
			}
			line.append(buffer[index]);
		}
		return line.toString();
	}

	public void close() throws IOException, IllegalStateException {
		if (closed) {
			throw new IllegalStateException("Scanner is already closed");
		}
		reader.close();
		closed = true;
	}
}