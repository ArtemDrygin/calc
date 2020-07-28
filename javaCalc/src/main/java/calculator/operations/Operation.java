package calculator.operations;

public interface Operation {
    String getOperator();

    Integer apply(Integer... numbers);

    void checkParametersCount(Integer parametersCount);
}
