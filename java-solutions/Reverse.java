import java.util.*;
import java.io.*;

public class Reverse {
    public static int[] getNumbers(String line) {
        if (line.length() == 0) {
            return new int[0];
        }
        int[] numbers = new int[1];
        int ptr = 0;
        StringBuilder num = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
        	char c = line.charAt(i);
        	boolean skip = !(Character.isDigit(c) || c == '-' || c == '+');
        	if  (!skip) {
        		num.append(c);
        	}
        	if ((skip || i + 1 == line.length()) && num.length() > 0) {
        		int number = Integer.parseInt(num.toString());
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