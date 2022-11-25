package expression;

import java.util.Objects;

public class BinaryExpression extends AbstractArgument {
    protected final char symbol;
    private final AbstractArgument ex1;
    private final AbstractArgument ex2;

    public BinaryExpression(AbstractArgument ex1, AbstractArgument ex2, int priority, char symbol) {
        this.ex1 = ex1;
        this.ex2 = ex2;
        this.priority = priority;
        this.symbol = symbol;
    }

    @Override
    public int evaluate(int point) {
        final int leftValue = ex1.evaluate(point);
        final int rightValue = ex2.evaluate(point);
        switch (symbol) {
            case '+':
                return leftValue + rightValue;
            case '-':
                return leftValue - rightValue;
            case '*':
                return leftValue * rightValue;
            case '/':
                return leftValue / rightValue;
            default:
                throw new AssertionError("Unsupported arithmetic operation");
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(").append(ex1.toString())
                            .append(String.format(" %c ", symbol))
                            .append(ex2.toString()).append(")");
        return sb.toString();
    }

    public String toMiniString() {
        StringBuilder sb = new StringBuilder();
        if (ex1.getPriority() > priority) {
            sb.append("(" + ex1.toMiniString() + ")");
        }
        else {
            sb.append(ex1.toMiniString());
        }
        sb.append(String.format(" %c ", symbol));
        if (ex2.getPriority() > priority) {
            sb.append("(" + ex2.toMiniString() + ")");
        }
        else {
            sb.append(ex2.toMiniString());
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || other.getClass() != getClass()) {
            return false;
        }
        return ex1.equals(((BinaryExpression) other).ex1) && ex2.equals(((BinaryExpression) other).ex2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ex1, ex2, symbol);
    }
}