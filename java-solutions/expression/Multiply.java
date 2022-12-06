package expression;

public class Multiply extends BinaryExpression{

    public Multiply(AbstractArgument ex1, AbstractArgument ex2) {
        super(ex1, ex2, 1, '*');
    }

    @Override
    protected int operate(int a, int b) {
        return a * b;
    }
    
}
