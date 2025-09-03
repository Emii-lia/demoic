package github.emiilia

object Operation: OperationInterface {

    override fun sum(a: Int, b: Int): Int = a + b

    override fun multiply(a: Int, b: Int): Int = a * b

    override fun divide(a: Int, b: Int): Int {
        return if (b == 0) {
            throw IllegalArgumentException("Division by zero is not allowed.")
        } else {
            a / b
        }
    }
}