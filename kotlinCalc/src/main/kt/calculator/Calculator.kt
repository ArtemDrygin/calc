package calculator

import calculator.operations.*
import calculator.service.Converter
import calculator.service.RomanConverter
import java.util.*

class Calculator(
    private val converter: Converter?,
    private val operations: Set<Operation>,
    private val min: Int = 1,
    private val max: Int = 10
) {

    constructor(operations: Set<Operation>) : this(null, operations)

    fun run() {
        while (true) {
            try {
                println(calc(readLine().toString()))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun calc(expresion: String): String? {
        for (operation in operations) {
            if (expresion.contains(operation.operator)) {
                val numbers = expresion.split(operation.operator).map { it.trim() }

                operation.checkParametersCount(numbers.size)

                val needConversion = isNeedConversion(numbers)

                val arabicNumbers = if (needConversion) convertNumbers(numbers) else parseNumbers(numbers)

                checkIsValidNumbers(arabicNumbers)

                val result = operation.apply(arabicNumbers)

                return if (needConversion) converter!!.convert(result) else result.toString()
            }
        }
        throw UnsupportedOperationException(
            """
                неверное выражение, не найден оператор
                доступные операторы: $operations
                """.trimIndent()
        )
    }

    private fun parseNumbers(numbers: List<String>): List<Int> = numbers.map { it.toInt() }

    private fun convertNumbers(numbers: List<String>): List<Int> {
        return if (numbers.all { converter!!.isValid(it) }) {
            numbers.map { converter!!.convert(it) }
        } else {
            throw IllegalArgumentException("все числа должны использовать один формат обозначения(Римские, Арабские и т.д.)")
        }
    }


    private fun checkIsValidNumbers(numbers: List<Int>) {
        if (numbers.any { !isValidNumber(it) })
            throw java.lang.IllegalArgumentException("""число должно быть в пределах [$min, $max]""")
    }

    private fun isNeedConversion(numbers: List<String>): Boolean {
        converter ?: return false
        return numbers.any { !isArabicNumber(it) }
    }

    private fun isArabicNumber(number: String): Boolean = number.matches("""\d*""".toRegex())

    private fun isValidNumber(number: Int): Boolean = number in min..max

    fun createBasicOperations(): Set<Operation> {
        val operations = HashSet<Operation>()
        operations.add(Plus())
        operations.add(Minus())
        operations.add(Multiply())
        operations.add(Div())
        return operations
    }

    companion object {
        fun createBasicOperations(): Set<Operation> {
            val operations = HashSet<Operation>()
            operations.add(Plus())
            operations.add(Minus())
            operations.add(Multiply())
            operations.add(Div())
            return operations
        }

        fun createCalc() = Calculator(RomanConverter, createBasicOperations())
    }
}

fun main() {
    Calculator(RomanConverter, Calculator.createBasicOperations()).run()
}