import java.util.Scanner;
import java.util.Arrays;

public class Reverse {
    public static int[] reverse(int[] seq, int right) {
        int[] reversed = new int[right];
        for (int i = right - 1, j = 0; i >= 0; i--, j++) {
            reversed[j] = seq[i];
        }
        return reversed;
    }

    public static int[] getNumbers(String line) {
        if (line.length() == 0) {
            return new int[0];
        }
        int[] numbers = new int[1];
        int ptr = 0;
        Scanner sc = new Scanner(line);
        while (sc.hasNextInt()) {
            int cur = sc.nextInt();
            if (ptr >= numbers.length) {
                numbers = Arrays.copyOf(numbers, (numbers.length * 3 + 1) / 2);
            }
            numbers[ptr++] = cur;
        }
        return reverse(numbers, ptr);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] lines = new int[1][];
        int ptr = 0;
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
}