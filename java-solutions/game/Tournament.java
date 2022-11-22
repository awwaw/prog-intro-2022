package game;

public class Tournament {
    private final Board board;
    private final Player[] players;
    private int[] scoreboard;

    public Tournament(Board board, Player[] players) {
        this.board = board;
        this.players = players;
        int n = players.length;
        scoreboard = new int[n];
    }

    public void play(boolean log) {
        int n = players.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    System.out.println(String.format("Player №%d - X", i + 1));
                    System.out.println(String.format("Player №%d - O", j + 1));
                    System.out.println("\nSTART\n");
                    Player player1 = players[i];
                    Player player2 = players[j];
                    Game game = new Game(board, new Player[]{player1, player2});
                    final int result = game.play(log);
                    if (result == 0) {
                        scoreboard[i]++;
                        scoreboard[j]++;
                    }
                    else if (result == 1) {
                        scoreboard[i] += 3;
                    }
                    else if (result == 2) {
                        scoreboard[j] += 3;
                    }
                    board.clear();
                }
            }
        }
    }

    public String getResults() {
        StringBuilder sb = new StringBuilder("\n-------\n");
        for (int i = 0; i < scoreboard.length; i++) {
            sb.append("Player №").append(i + 1).append(" - ").append(scoreboard[i]).append("\n");
        }
        sb.append("-------------\n");
        return sb.toString();
    }
}
