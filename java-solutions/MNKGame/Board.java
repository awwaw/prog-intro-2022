package MNKGame;

public interface Board {
    SafePosition getPosition();

    Result makeMove(Move move);
}