package calculator.operations

import java.util.*

abstract class AbstractBasicOperation(override val operator: String) : Operation {
    override fun apply(numbers: List<Int>): Int {
        checkParametersCount(numbers.size)
        return apply(numbers[0], numbers[1])
    }

    protected open fun apply(a: Int, b: Int): Int {
        return a / b
    }

    override fun checkParametersCount(parametersCount: Int) {
        require(parametersCount == PARAMETERS_COUNT) {
            """неверное колличество параметров, операции '$operator' требуется $PARAMETERS_COUNT параметров"""
        }
    }

    override fun toString(): String {
        return operator
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val that = other as AbstractBasicOperation
        return operator == that.operator
    }

    override fun hashCode(): Int {
        return Objects.hash(operator)
    }

    companion object {
        var PARAMETERS_COUNT = 2
    }
}