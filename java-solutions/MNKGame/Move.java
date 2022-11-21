package MNKGame;

public class Move {
    private final int row;
    private final int col;
    private final Cell turn;

    public Move(int row, int col, Cell turn) {
        this.row = row;
        this.col = col;
        this.turn = turn;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Cell getTurn() {
        return turn;
    }

    @Override
    public String toString() {
        return String.format("Move(%s, %d, %d)", turn, row + 1, col + 1);
    }
}
