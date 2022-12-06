package expression;

public class Add extends BinaryExpression {

    public Add(AbstractArgument ex1, AbstractArgument ex2) {
        super(ex1, ex2, 2, '+', true);
    }

    @Override
    protected int operate(int a, int b) {
        return a + b;
    }
    
    @Override
    protected double operate(double a, double b) {
        return a + b;
    }
}
