package calculator.operations

class Multiply : AbstractBasicOperation("*") {
    public override fun apply(a: Int, b: Int): Int {
        return a * b
    }
}