package calculator.service;

public class RomanConverter implements Converter {
    public final static String SUPPORTED_ROMAN_NUMBER_PATTERN = "C|((XC|XL)|(L?X{0,3}))?((IX)|(IV)|(V?I{0,3}))";

    private static RomanConverter instance;

    private RomanConverter() {
    }

    public static RomanConverter getInstance() {
        if (null == instance) {
            instance = new RomanConverter();
        }

        return instance;
    }


    @Override
    public Integer convert(String romanNumber) {
        if (!isValid(romanNumber)) {
            throw new IllegalArgumentException("число '%s' введено некорректно, поддерживается конвертация римских чисел только от 1 до 100");
        }

        int value = 0;
        int index = 0;
        for (RomanNumberAnchor anchor : RomanNumberAnchor.values()) {
            if (romanNumber.startsWith(anchor.toString(), index)) {
                value += anchor.getValue();
                index += anchor.toString().length();
            }
        }

        return value;
    }

    @Override
    public String convert(Integer number) {
        if (!isValid(number)) {
            throw new IllegalArgumentException("не поддерживается конвертация чисел больше 100 или меньше 1");
        }

        StringBuilder romanNumber = new StringBuilder();
        for (RomanNumberAnchor anchor : RomanNumberAnchor.values()) {
            if (number >= anchor.getValue()) {
                number -= anchor.getValue();
                romanNumber.append(anchor);
            }
        }

        return romanNumber.toString();
    }


    @Override
    public boolean isValid(String romanNumberString) {
        return romanNumberString.matches(SUPPORTED_ROMAN_NUMBER_PATTERN);
    }


    @Override
    public boolean isValid(Integer number) {
        return number >= 1 && number <= 100;
    }

    private enum RomanNumberAnchor {
        C(100),
        XC(90),
        L(50),
        XL(40),
        XXX(30),
        XX(20),
        X(10),
        IX(9),
        V(5),
        IV(4),
        III(3),
        II(2),
        I(1);

        private final int value;

        RomanNumberAnchor(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
