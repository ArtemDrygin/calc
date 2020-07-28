package calculator;

import calculator.operations.*;
import calculator.service.Converter;
import calculator.service.RomanConverter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

public class Calculator {
    private final Converter converter;
    private final Set<Operation> operations;
    private final int min;
    private final int max;

    public Calculator(Set<Operation> operations) {
        this(null, operations);
    }

    public Calculator(Converter converter, Set<Operation> operations) {
        this(converter, operations, 1, 10);
    }

    public Calculator(Converter converter, Set<Operation> operations, int min, int max) {
        this.converter = converter;
        this.operations = operations;
        this.min = min;
        this.max = max;
    }

    public Converter getConverter() {
        return converter;
    }

    public Set<Operation> getOperations() {
        return operations;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public static void main(String[] args) {
        new Calculator(RomanConverter.getInstance(), createBasicOperations()).run();
    }

    private static Set<Operation> createBasicOperations() {
        HashSet<Operation> operations = new HashSet<>();
        operations.add(new Plus());
        operations.add(new Minus());
        operations.add(new Multiply());
        operations.add(new Div());

        return operations;
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println(calc(scanner.nextLine()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String calc(String expresion) {
        for (Operation operation : getOperations()) {
            if (expresion.contains(operation.getOperator())) {
                String[] numbers = Arrays.stream(expresion.split(Pattern.quote(operation.getOperator())))
                        .map(String::trim)
                        .toArray(String[]::new);

                operation.checkParametersCount(numbers.length);

                boolean needConversion = isNeedConversion(numbers);

                Integer[] arabicNumbers = needConversion ? convertNumbers(numbers) : parseNumbers(numbers);

                checkIsValidNumbers(arabicNumbers);

                Integer result = operation.apply(arabicNumbers);

                return needConversion ? getConverter().convert(result) : result.toString();
            }
        }

        throw new UnsupportedOperationException("неверное выражение, не найден оператор\n" +
                "доступные операторы: " + getOperations());
    }

    private Integer[] parseNumbers(String[] numbers) {
        return Arrays.stream(numbers)
                .map(Integer::parseInt)
                .toArray(Integer[]::new);
    }

    private Integer[] convertNumbers(String[] numbers) {
        for (String number : numbers) {
            if (!converter.isValid(number)) {
                throw new IllegalArgumentException("все числа должны использовать один формат обозначения(Римские, Арабские и т.д.)");
            }
        }

        return Arrays.stream(numbers)
                .map(getConverter()::convert)
                .toArray(Integer[]::new);
    }

    private void checkIsValidNumbers(Integer[] numbers) {
        for (Integer number : numbers) {
            if (!isValidNumber(number)) {
                throw new IllegalArgumentException(String.format("число должно быть в пределах [%d, %d]", getMin(), getMax()));
            }
        }
    }

    private boolean isNeedConversion(String... numbers) {
        if (null == getConverter()) {
            return false;
        }

        for (String number : numbers) {
            if (!isArabicNumber(number)) {
                return true;
            }
        }

        return false;
    }

    private boolean isArabicNumber(String number) {
        return number.matches("\\d*");
    }

    private boolean isValidNumber(Integer number) {
        return number >= getMin() && number <= getMax();
    }


}
