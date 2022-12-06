package expression;

public class Const extends AbstractArgument {
    public final int value;
    public final double doubleValue;
    private final boolean doubleType;
    public final int priority;

    public Const(int value) {
        this.value = value;
        doubleValue = 0;
        priority = -1;
        doubleType = false;
        isAssociative = true;
    }

    public Const(double value) {
        doubleValue = value;
        this.value = 0;
        priority = -1;
        doubleType = true;
    }

    @Override
    public int evaluate(int value) {
        return this.value;
    }

    @Override
    public String toString() {
        if (doubleType) {
            return String.valueOf(doubleValue);
        }
        return String.valueOf(value);
    }

    @Override
    public String toMiniString() {
        if (doubleType) {
            return String.valueOf(doubleValue);
        }
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || other.getClass() != getClass()) {
            return false;
        }
        Const o = (Const) other;
        if (doubleType) {
            return doubleValue == o.doubleValue;
        }
        return value == o.value;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public int hashCode() {
        if (doubleType) {
            return Double.hashCode(doubleValue);
        }
        return Integer.hashCode(value);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return value;
    }

    @Override
    public double evaluate(double x) {
        return doubleValue;
    }

    @Override
    protected int operate(int a, int b) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    protected double operate(double a, double b) {
        // TODO Auto-generated method stub
        return 0;
    }
}
