package game;

public class Game {
    private final Board board;
    private final Player[] players;

    public Game(Board board, Player[] players) {
        this.board = board;
        this.players = players;
    }

    public int play(boolean log) {
        while (true) {
            for (int i = 0; i < players.length; i++) {
                final int result = makeMove(players[i], i + 1, log);
                if (result != -5) { // :NOTE: хардкодить -5 - не надо
                    return result;
                }
            }
        }
    }

    private int makeMove(Player player, int no, boolean log) {
        final Move move = player.makeMove(board.getPosition()); // :NOTE:  выкине эксепшен и никак его не поймает
        final Result result = board.makeMove(move);
        if (log) {
            System.out.println();
            System.out.println("Player: " + no);
            System.out.println(move);
            System.out.println(board);
            System.out.println("Result: " + result);
        }
        switch (result) {
            case WIN:
                return no;
            case LOSE:
                return -no;
            case DRAW:
                return 0;
            case UNKNOWN:
                return -5;
            default:
                throw new AssertionError("Unknown makeMove result " + result);
        }
    }
}
