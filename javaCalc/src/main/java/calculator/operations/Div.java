package calculator.operations;

public class Div extends AbstractBasicOperation {
    public Div() {
        super("/");
    }

    @Override
    public Integer apply(Integer a, Integer b) {
        return a / b;
    }
}
