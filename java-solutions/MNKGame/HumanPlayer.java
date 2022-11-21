package MNKGame;

import java.util.Scanner;
import java.util.regex.Pattern;

public class HumanPlayer implements Player {
    private final Scanner in;

    public HumanPlayer(Scanner in) {
        this.in = in;
    }

    @Override
    public Move makeMove(SafePosition position) {
        System.out.println();
        System.out.println("Current position");
        System.out.println(position);
        System.out.println("Enter you move for " + position.getTurn());
        String inputLine = in.nextLine();
        while (!isCorrectInput(inputLine)) {
            System.out.println("bruh you entered bad input: it must contain 2 numbers");
            inputLine = in.nextLine();
        }
        int[] numbers = parseCorrectInput(inputLine);
        int x = numbers[0];
        int y = numbers[1];
        return new Move(x - 1, y - 1, position.getTurn());
    }

    private String[] getNumbers(String inputLine) {
        String[] res = new String[2];
        int ind = inputLine.indexOf(" ");
        String num1 = inputLine.substring(0, ind);
        String num2 = inputLine.substring(ind + 1, inputLine.length());
        res[0] = num1;
        res[1] = num2;
        return res;
    }

    private boolean isCorrectInput(String inputLine) {
        Pattern pattern = Pattern.compile("-?\\d+ -?\\d+");
        return pattern.matcher(inputLine).matches();
    }

    private int[] parseCorrectInput(String inputLine) {
        String[] numbers = getNumbers(inputLine);
        int[] res = new int[2];
        int num1 = Integer.parseInt(numbers[0]);
        int num2 = Integer.parseInt(numbers[1]);
        res[0] = num1;
        res[1] = num2;
        return res;
    }
}
