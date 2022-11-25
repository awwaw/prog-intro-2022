package expression;

public class Add extends BinaryExpression {

    public Add(AbstractArgument ex1, AbstractArgument ex2) {
        super(ex1, ex2, 2, '+');
    }
    
}
