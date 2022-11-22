package game;

import java.util.Map;

public class SafePosition implements Position {

    private static final Map<Cell, String> CELL_TO_STRING = Map.of(
            Cell.E, ".",
            Cell.X, "X",
            Cell.O, "0"
    );

    private final Cell[][] field;
    private Cell turn;
    private final int n;
    private final int m;

    public SafePosition(MNKBoard board) {
        n = board.getRowCount();
        m = board.getColCount();
        turn = board.getTurn();
        field = new Cell[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                field[i][j] = board.getCell(i, j);
            }
        }
    }

    @Override
    public Cell getTurn() {
        return turn;
    }

    @Override
    public boolean isValid(Move move) {
        return (0 <= move.getRow() && move.getRow() < n) &&
                (0 <= move.getCol() && move.getCol() < m) &&
                field[move.getRow()][move.getCol()] == Cell.E &&
                turn == move.getTurn();
    }

    @Override
    public Cell getCell(int row, int column) {
        return field[row][column];
    }     

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(CELL_TO_STRING.get(field[i][j]));
            }
            sb.append("\n");
        }
        sb.append("-----------\n\n");
        return sb.toString();
    }
}
