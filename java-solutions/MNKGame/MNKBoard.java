package MNKGame;

import java.util.Arrays;
import java.util.Map;

public class MNKBoard implements Board, Position {

    private static final Map<Cell, String> CELL_TO_STRING = Map.of(
            Cell.E, ".",
            Cell.X, "X",
            Cell.O, "0"
    );

    private final Cell[][] field;
    private Cell turn;
    private final int n;
    private final int m;
    private final int k;

    public MNKBoard(int n, int m, int k) {
        field = new Cell[n][m];
        for (Cell[] row : field) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
        this.n = n;
        this.m = m;
        this.k = k;
    }

    @Override
    public SafePosition getPosition() {
        return new SafePosition(this);
    }

    @Override
    public Result makeMove(Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }
        field[move.getRow()][move.getCol()] = move.getTurn();
        if (checkWin(move)) {
            return Result.WIN;
        }
        if (checkDraw()) {
            return Result.DRAW;
        }

        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNKNOWN;
    }

    private boolean isValidPosition(int x, int y, Cell turn) {
        return (0 <= x && x < n) &&
                (0 <= y && y < m) && 
                (field[x][y] == turn);
    }

    private int count(Move move, int dirX, int dirY) {
        int cnt = 0;
        for (int ptr1 = move.getRow(), ptr2 = move.getCol();
             isValidPosition(ptr1, ptr2, move.getTurn());
              ptr1 += dirX, ptr2 += dirY)
        {
            cnt++;
            if (cnt > k) break;
        }
        return cnt;
    }

    private boolean checkWin(Move move) {
        final int[][] directions = new int[][]{
            {0, 1}, {0, -1},
            {1, 0}, {-1, 0},
            {1, 1}, {-1, -1},
            {1, -1}, {-1, 1}
        };
        for (int i = 0; i < 8; i += 2) {
            int[] dir1 = directions[i];
            int[] dir2 = directions[i + 1];
            int cnt = 0;
            cnt += count(move, dir1[0], dir1[1]);
            cnt += count(move, dir2[0], dir2[1]);
            if (cnt - 1 >= k) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDraw() {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (field[i][j] == Cell.E) {
                    cnt++;
                }
            }
        }
        if (cnt == 0) {
            return true;
        }
        return false;
    }

    @Override
    public Cell getTurn() {
        return turn;
    }

    public int getRowCount() {
        return n;
    }

    public int getColCount() {
        return m;
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
