package calculator.service

interface Converter {
    fun convert(numberString: String): Int
    fun convert(number: Int): String
    fun isValid(number: String): Boolean
    fun isValid(number: Int): Boolean
}