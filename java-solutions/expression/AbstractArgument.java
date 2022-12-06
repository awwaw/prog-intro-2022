package expression;

public abstract class AbstractArgument implements Expression, TripleExpression, DoubleExpression {
    protected int priority;

    public int getPriority() {
        return priority;
    }

    public abstract boolean equals(Object other);

    protected abstract int operate(int a, int b);

    public abstract int hashCode();
}
