import java.util.Scanner;
import java.util.Arrays;

public class ReverseAvg {
	public static int[] getNumbers(String line) {
        if (line.length() == 0) {
            return new int[0];
        }
        int[] numbers = new int[2];
        int ptr = 0;
        Scanner sc = new Scanner(line);
        while (sc.hasNextInt()) {
            int cur = sc.nextInt();
            if (ptr >= numbers.length) {
                numbers = Arrays.copyOf(numbers, (numbers.length * 3 + 1) / 2);
            }
            numbers[ptr++] = cur;
        }
        if (ptr == numbers.length) {
            numbers = Arrays.copyOf(numbers, numbers.length + 1);
        }
        numbers[numbers.length - 1] = ptr;
        return numbers;
    }

	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] lines = new int[1][];
        int ptr = 0;
        int maxLine = 0;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (ptr >= lines.length) {
                lines = Arrays.copyOf(lines, (lines.length * 3 + 1) / 2);
            }
            int[] numbers = getNumbers(line);
            lines[ptr++] = numbers;
            if (numbers.length != 0) {
                maxLine = Math.max(maxLine, numbers[numbers.length - 1]);
            }
        }
        long[] linesSum = new long[ptr];
        long[] columnsSum = new long[maxLine];
        int[] columnsCnt = new int[maxLine];

        for (int i = 0; i < ptr; i++) {
        	long sm = 0;
            if (lines[i].length == 0)
                continue;
        	for (int j = 0; j < lines[i][lines[i].length - 1]; j++) {
        		sm += lines[i][j];
        	}
        	linesSum[i] = sm;
        }

        for (int j = 0; j < maxLine; j++) {
        	long sm = 0;
        	int cnt = 0;
        	for (int i = 0; i < ptr; i++) {
        		if (lines[i] != null && lines[i].length > j && j < lines[i][lines[i].length - 1]) {
        			sm += lines[i][j];
        			cnt++;
        		}
        	}
        	columnsSum[j] = sm;
        	columnsCnt[j] = cnt;
        }

        for (int i = 0; i < lines.length; i++) {
            if (lines[i] != null) {
                if (lines[i].length == 0) {
                    System.out.println();
                    continue;
                }
                for (int j = 0; j < lines[i][lines[i].length - 1]; j++) {
                	int minus = 0;
                	if (columnsCnt[j] > 1) {
                		minus = 1;
                	}
                    System.out.print((linesSum[i] + columnsSum[j] - lines[i][j]) / 
                    	(lines[i][lines[i].length - 1] + columnsCnt[j] - 1) + " ");
                }
                System.out.println();
            }
        }
    }
}