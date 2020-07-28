package calculator.operations

class Div : AbstractBasicOperation("/") {
    public override fun apply(a: Int, b: Int): Int {
        return a / b
    }
}