package expression;

public class Divide extends BinaryExpression {

    public Divide(AbstractArgument ex1, AbstractArgument ex2) {
        super(ex1, ex2, 1, '/');
    }

    @Override
    protected int operate(int a, int b) {
        return a / b;
    }
    
}
