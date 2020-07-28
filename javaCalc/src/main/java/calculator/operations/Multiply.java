package calculator.operations;

public class Multiply extends AbstractBasicOperation {
    public Multiply() {
        super("*");
    }

    @Override
    public Integer apply(Integer a, Integer b) {
        return a * b;
    }
}
