package calculator.operations;

public class Plus extends AbstractBasicOperation {
    public Plus() {
        super("+");
    }

    @Override
    public Integer apply(Integer a, Integer b) {
        return a + b;
    }
}
