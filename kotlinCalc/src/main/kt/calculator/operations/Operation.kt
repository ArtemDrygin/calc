package calculator.operations

interface Operation {
    val operator: String
    fun apply( numbers: List<Int>): Int
    fun checkParametersCount(parametersCount: Int)
}