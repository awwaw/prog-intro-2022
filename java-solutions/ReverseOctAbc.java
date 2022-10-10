import java.util.*;
import java.io.*;

public class ReverseOctAbc {
    private static boolean check(char c) {
        return (Character.isDigit(c) ||
                ((int)'a' <= (int)c && (int)c <= (int)'j') ||
                (c == '-' || c == '+') ||
                (c == 'o' || c == 'O'));
    }

    private static char convert(char c) {
    	return (char)((int)c - (int)'a' + (int)'0');
    }

    private static int[] getNumbers(String line) {
        if (line.length() == 0) {
            return new int[0];
        }
        int[] numbers = new int[1];
        int ptr = 0;
        StringBuilder num = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
        	char c = line.charAt(i);
        	boolean skip = !(check(c));
        	if (!skip) {
        		if ((int)'a' <= (int)c && (int)c <= (int)'j') {
        			c = convert(c);
        		}
        		num.append(c);
        	}
        	if ((skip || i + 1 == line.length()) && num.length() > 0) {
        		int base = 10;
        		if (num.charAt(num.length() - 1) == 'o' || num.charAt(num.length() - 1) == 'O') {
        			num.setLength(num.length() - 1);
        			base = 8;
        		}
        		int number = (int)Long.parseLong(num.toString(), base);
        		if (ptr >= numbers.length) {
        			numbers = Arrays.copyOf(numbers, (numbers.length * 3 + 1) / 2);
        		}
        		numbers[ptr++] = number;
        		num = new StringBuilder();
        	}
        }
        numbers = Arrays.copyOf(numbers, ptr);
        int[] reversed = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
        	reversed[i] = numbers[numbers.length - 1 - i];
        }
        return reversed;
    }

    public static void main(String[] args) {
    	try {
	        MyScanner sc = new MyScanner(new BufferedReader(
											new InputStreamReader(System.in)));
	        int[][] lines = new int[1][];
	        int ptr = 0;
	        try {
		        while (sc.hasNextLine()) {
		            String line = sc.nextLine();
		            if (ptr >= lines.length) {
		                lines = Arrays.copyOf(lines, (lines.length * 3 + 1) / 2);
		            }
		            lines[ptr++] = getNumbers(line);
		        }
		        for (int i = lines.length - 1; i >= 0; i--) {
		            if (lines[i] != null) {
		                for (int num : lines[i]) {
		                    System.out.print(num + " ");
		                }
		                System.out.println("");
		            }
		        }
		    }
		    finally {
		    	sc.close();
		    }
	    } catch (IOException e) {
	    	System.out.println("An error occured while reading from input: " + e.getMessage());
	    }
    }
}