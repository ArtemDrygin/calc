package calculator.service

object RomanConverter : Converter {
    private const val SUPPORTED_ROMAN_NUMBER_PATTERN = "C|((XC|XL)|(L?X{0,3}))?((IX)|(IV)|(V?I{0,3}))"

    override fun convert(numberString: String): Int {
        require(isValid(numberString)) { "число '%s' введено некорректно, поддерживается конвертация римских чисел только от 1 до 100" }

        var value = 0
        var index = 0
        RomanNumberAnchor.values().forEach {
            if (numberString.startsWith(it.toString(), index)) {
                value += it.value
                index += it.toString().length
            }
        }

        return value
    }

    @Suppress("NAME_SHADOWING")
    override fun convert(number: Int): String {
        require(isValid(number)) { "не поддерживается конвертация чисел больше 100 или меньше 1" }

        var number = number
        with(StringBuilder(), {
            RomanNumberAnchor.values().forEach {
                if (number >= it.value) {
                    number -= it.value
                    append(it)
                }
            }
            return toString()
        })
    }

    override fun isValid(number: String): Boolean = number.matches(SUPPORTED_ROMAN_NUMBER_PATTERN.toRegex())


    override fun isValid(number: Int): Boolean = number in 1..100

    private enum class RomanNumberAnchor(val value: Int) {
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
    }
}