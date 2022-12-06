package expression;

import java.util.Objects;

public abstract class BinaryExpression extends AbstractArgument {
    protected final char symbol;
    private final AbstractArgument ex1;
    private final AbstractArgument ex2;

    public BinaryExpression(AbstractArgument ex1,
                             AbstractArgument ex2,
                              int priority,
                               char symbol,
                               boolean isAssociative) {
        this.ex1 = ex1;
        this.ex2 = ex2;
        this.priority = priority;
        this.symbol = symbol;
        this.isAssociative = isAssociative;
    }

    protected abstract int operate(int a, int b);
    protected abstract double operate(double a, double b);

    @Override
    public int evaluate(int point) {
        return operate(ex1.evaluate(point), ex2.evaluate(point));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(").append(ex1.toString())
                            .append(String.format(" %c ", symbol))
                            .append(ex2.toString()).append(")");
        return sb.toString();
    }

    public String toMiniString() {
        int p1 = ex1.getPriority();
        int p2 = ex2.getPriority();
        int p = this.getPriority();
        StringBuilder sb = new StringBuilder();
        if (p1 == -1 || p == 2 || (p == 1 && p1 == 1)) {
            sb.append(ex1.toMiniString());
        }
        else {
            sb.append("(" + ex1.toMiniString() + ")");
        }
        sb.append(String.format(" %c ", symbol));
        if (this.isAssociative) {
            if (symbol == '*') {
                if (p2 < 0 || (ex2.isAssociative && p2 == p)) {
                    sb.append(ex2.toMiniString());
                }
                else {
                    sb.append("(" + ex2.toMiniString() + ")");
                }
            }
            else {
                sb.append(ex2.toMiniString());
            }
        }
        else {
            if (p2 >= p) {
                sb.append("(" + ex2.toMiniString() + ")");
            }
            else {
                sb.append(ex2.toMiniString());
            }
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
        return ex1.equals(((BinaryExpression) other).ex1) &&
                 ex2.equals(((BinaryExpression) other).ex2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ex1, ex2, symbol);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return operate(ex1.evaluate(x, y, z), ex2.evaluate(x, y, z));
    }

    @Override
    public double evaluate(double x) {
        return operate(ex1.evaluate(x), ex2.evaluate(x));
    }
}