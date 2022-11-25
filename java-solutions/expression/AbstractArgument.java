package expression;

public abstract class AbstractArgument implements Expression {
    protected int priority;

    public int getPriority() {
        return priority;
    }

    public abstract boolean equals(Object other);

    public abstract int hashCode();
}
