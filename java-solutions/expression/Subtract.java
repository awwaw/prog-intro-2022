package expression;

public class Subtract extends BinaryExpression {

    public Subtract(AbstractArgument ex1, AbstractArgument ex2) {
        super(ex1, ex2, 2, '-');
    }
    
}
