package expression;

public class Const extends AbstractArgument {
    public final int value;
    public final int priority;

    public Const(int value) {
        this.value = value;
        priority = -1;
    }

    @Override
    public int evaluate(int value) { // TODO: разобраться с этим аргументом
        return this.value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public String toMiniString() {
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
        return value == o.value;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }
}
