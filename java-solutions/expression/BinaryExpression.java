package expression;

import java.util.Objects;

public abstract class BinaryExpression extends AbstractArgument {
    protected final char symbol;
    private final AbstractArgument ex1;
    private final AbstractArgument ex2;

    public BinaryExpression(AbstractArgument ex1, AbstractArgument ex2, int priority, char symbol) {
        this.ex1 = ex1;
        this.ex2 = ex2;
        this.priority = priority;
        this.symbol = symbol;
    }

    protected abstract int operate(int a, int b);

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

    private String processMinus() {
        StringBuilder sb = new StringBuilder();
        BinaryExpression expression = (BinaryExpression) ex2;
        sb.append(ex1.toMiniString());
        sb.append(" - ");
        if (expression.priority == 2) {
            sb.append("(" + expression.toMiniString() + ")");
        }
        else {
            sb.append(expression.toMiniString());
        }
        return sb.toString();
    }

    private String addLeftArgument() {
        StringBuilder sb = new StringBuilder();
        if (ex1.getPriority() > priority) {
            sb.append("(" + ex1.toMiniString() + ")");
        }
        else {
            sb.append(ex1.toMiniString());
        }
        return sb.toString();
    }

    private String processMultiply() {
        StringBuilder sb = new StringBuilder();
        sb.append(addLeftArgument());
        sb.append(" * ");
        BinaryExpression expression = (BinaryExpression) ex2;
        if (expression.symbol != '*') {
            sb.append("(" + expression.toMiniString() + ")");
        }
        else {
            sb.append(expression.toMiniString());
        }
        return sb.toString();
    }

    private String processDivide() {
        StringBuilder sb = new StringBuilder();
        sb.append(addLeftArgument());
        sb.append(" / ");
        BinaryExpression expression = (BinaryExpression) ex2;
        sb.append("(" + expression.toMiniString() + ")");
        return sb.toString();
    }

    private String basicProcessing() {
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

    public String toMiniString() {
        if (ex2 instanceof BinaryExpression expression) {
            switch (symbol) {
                case '-':
                    return processMinus();
                case '*':
                    return processMultiply();
                case '/':
                    return processDivide();
                default:
                    return basicProcessing();
            }
        }
        return basicProcessing();
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
        final int leftValue = ex1.evaluate(x, y, z);
        final int rightValue = ex2.evaluate(x, y, z);
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

    @Override
    public double evaluate(double x) {
        final double leftValue = ex1.evaluate(x);
        final double rightValue = ex2.evaluate(x);
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
}