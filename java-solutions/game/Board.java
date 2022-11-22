package game;

public interface Board {
    SafePosition getPosition();

    Result makeMove(Move move);
}