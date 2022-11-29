package game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        // Tournament tournament = new Tournament(
        //         new MNKBoard(n, m, k, 2),
        //         new Player[]{
        //             new HumanPlayer(new Scanner(System.in)),
        //             new HumanPlayer(new Scanner(System.in)), 
        //             new HumanPlayer(new Scanner(System.in))
        //         }
        // );
        // tournament.play(true);
        // System.out.println(tournament.getResults());

        // :NOTE: не могу запустить и посмотреть
        int playersCount = in.nextInt();
        final int result = new Game(
                new MNKBoard(n, m, k, playersCount, true),
                new Player[] {
                    new HumanPlayer(new Scanner(System.in)),
                    new HumanPlayer(new Scanner(System.in)),
                    new HumanPlayer(new Scanner(System.in))
                }
        ).play(true);
        in.close();
        if (result != -5) {
            if (result > 0) {
                System.out.println(String.format("Player %d won", result - 1));
            }
            else if (result == 0) {
                System.out.println("DRAW");
            }
        }
    }
}
