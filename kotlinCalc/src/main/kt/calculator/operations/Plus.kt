package calculator.operations

class Plus : AbstractBasicOperation("+") {
    public override fun apply(a: Int, b: Int): Int {
        return a + b
    }
}