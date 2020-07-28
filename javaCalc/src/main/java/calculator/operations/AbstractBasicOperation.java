package calculator.operations;

import java.util.Objects;

public abstract class AbstractBasicOperation implements Operation {
    public final static int PARAMETERS_COUNT = 2;
    private final String operator;

    AbstractBasicOperation(String operator) {
        this.operator = operator;
    }

    @Override
    public String getOperator() {
        return operator;
    }

    @Override
    public Integer apply(Integer... numbers) {
        checkParametersCount(numbers.length);
        return apply(numbers[0], numbers[1]);
    }

    protected abstract Integer apply(Integer a, Integer b);

    public void checkParametersCount(Integer parametersCount) {
        if (parametersCount != PARAMETERS_COUNT) {
            throw new IllegalArgumentException(String.format(
                    "неверное колличество параметров, операции '%s' требуется %d параметров",
                    getOperator(),
                    PARAMETERS_COUNT)
            );
        }
    }

    @Override
    public String toString() {
        return operator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractBasicOperation that = (AbstractBasicOperation) o;
        return Objects.equals(operator, that.operator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operator);
    }
}
