package calculator.operations;

public class Minus extends AbstractBasicOperation {
    public Minus() {
        super("-");
    }

    @Override
    public Integer apply(Integer a, Integer b) {
        return a - b;
    }
}
