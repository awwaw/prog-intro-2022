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
		if (this.closed) {
			throw new IllegalStateException("Scanner is already closed");
		}
		int read = this.reader.read(this.buffer);
		this.bufferSize = read;
		return read;
	}

	private boolean hasNextChar() throws IOException, IllegalStateException { // return true if there is a char left
		if (this.bufferSize == -1) {
			return false;
		}
		if (this.index == this.bufferSize - 1) {
			if (this.updateBuffer() > 0) {
				this.index = -1;
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

	private boolean checkForWord(char c) {
		return (Character.isLetter(c) ||
		 Character.getType(c) == Character.DASH_PUNCTUATION ||
		  c == '\'');
	}

	private boolean checkForNumber(char c) {
		return (Character.isDigit(c) || c == '-' || c == '+');
	}

	private String next(int type) throws IOException, IllegalStateException, NoSuchElementException { // 0 - word, 1 - number
		StringBuilder sb = new StringBuilder();
		if (!this.hasNextChar()) {
			throw new NoSuchElementException("Can't read more tokens");
		}
		while (this.hasNextChar()) {
			this.index++;
			char c = this.buffer[this.index];
			boolean ok = false;
			if (type == 0) {
				ok = checkForWord(c);
			}
			else if (type == 1) {
				ok = checkForNumber(c);
			}

			if (ok) {
				sb.append(c);
			}
			else {
				return sb.toString();
			}
		}
		return sb.toString();
	}

	public String nextToken() throws IOException, IllegalStateException, NoSuchElementException {
		return this.next(0);
	}

	public int nextInt() throws NumberFormatException, IOException, IllegalStateException, NoSuchElementException {
		return Integer.parseInt(this.next(1));
	}

	private boolean isNextLine() throws IllegalStateException, IOException, NoSuchElementException {
		if (this.buffer[this.index] == '\r') {
			if (this.hasNextChar()) {
				this.index++;
				if (this.buffer[this.index] == '\n') {
					// if (this.hasNextChar()) {
					// 	this.index++;
					// }
					return true;
				}
			}
			return true;
		}
		else if (this.buffer[this.index] == '\n' ||
					this.buffer[this.index] == '\u2028' ||
					this.buffer[this.index] == '\u2029' ||
					this.buffer[this.index] == '\u0085') {
			// if (this.hasNextChar()) {
			// 	this.index++;
			// }
			return true;
		}
		return false;
	}

	public String nextLine() throws IllegalStateException, IOException, NoSuchElementException {
		StringBuilder line = new StringBuilder();
		while (this.hasNextChar()) {
			this.index++;
			if (this.isNextLine()) {
				return line.toString();
			}
			line.append(this.buffer[this.index]);
		}
		return line.toString();
	}

	public void close() throws IOException, IllegalStateException {
		if (this.closed) {
			throw new IllegalStateException("Scanner is already closed");
		}
		this.reader.close();
		this.closed = true;
	}
}