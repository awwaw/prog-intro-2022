package expression;

public abstract class AbstractArgument implements Expression, TripleExpression, DoubleExpression {
    protected int priority;
    protected boolean isAssociative;

    public int getPriority() {
        return priority;
    }

    public abstract boolean equals(Object other);

    protected abstract int operate(int a, int b);
    protected abstract double operate(double a, double b);

    public abstract int hashCode();
}
