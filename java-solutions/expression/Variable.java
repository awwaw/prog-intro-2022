package expression;

public class Variable extends AbstractArgument {
    public final String name;
    public final int priority;

    public Variable(String name) {
        this.name = name;
        priority = -1;
    }

    @Override
    public int evaluate(int value) {
        return value;
    }

    @Override 
    public String toString() {
        return name;
    }

    @Override 
    public String toMiniString() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || other.getClass() != getClass()) {
            return false;
        }
        Variable o = (Variable) other;
        return name == o.name;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public int evaluate(int x, int y, int z) {
        switch (name) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
            default:
                throw new IllegalArgumentException("Illegal vaiable name");
        }
    }

    @Override
    public double evaluate(double x) {
        return x;
    }
}