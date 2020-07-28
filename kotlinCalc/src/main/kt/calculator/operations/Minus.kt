package calculator.operations

class Minus : AbstractBasicOperation("-") {
    public override fun apply(a: Int, b: Int): Int {
        return a - b
    }
}