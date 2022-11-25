package expression;

public class Main {
    public static void main(String[] args) {
        int value = Integer.parseInt(args[0]);
        // AbstractBinaryExpression expression = new Add(
        //     new Subtract(
        //         new Multiply(
        //             new Variable("x"),
        //             new Variable("x")
        //         ),
        //         new Multiply(
        //             new Const(2),
        //             new Variable("x")
        //         )
        //     ),
        //     new Const(1)
        // );
        // System.out.println(expression.evaluate(value));
        // System.out.println(expression);

        // System.out.println(new Subtract(
        //     new Multiply(
        //         new Const(2),
        //         new Variable("x")
        //     ),
        //     new Const(3)
        // ).toString());
        // System.out.println(new Subtract(
        //     new Multiply(
        //         new Const(2),
        //         new Variable("x")
        //     ),
        //     new Const(3)
        // ).toMiniString());

        Expression hard = new Multiply(
            new Add(
                new Multiply(
                    new Const(5), 
                    new Variable("x")
                ),
                new Const(3)
            ),
            new Subtract(
                new Multiply(
                    new Const(2), 
                    new Variable("y")
                ),
                new Const(4)
            )
        );
        System.out.println(hard.toMiniString());

        System.out.println(new Multiply(new Const(2), new Variable("x"))
                            .equals(new Multiply(new Const(2), new Variable("x"))));
        System.out.println(new Multiply(new Const(2), new Variable("x"))
                            .equals(new Multiply(new Variable("x"), new Const(2))));
    }
}
