import github.emiilia.Operation
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class OperationTest {
    @Test
    fun testAddition() {
        assertEquals(5, Operation.sum(2, 3))
    }

    @Test
    fun testSubtraction() {
        assertEquals(1, Operation.sum(3, -2))
    }

    @Test
    fun testMultiplication() {
        assertEquals(6, Operation.multiply(2, 3))
    }

    @Test
    fun testDivision() {
        assertEquals(2, Operation.divide(6, 3))
    }

    @Test
    fun testDivisionByZero() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            Operation.divide(6, 0)
        }
        assertEquals("Division by zero is not allowed.", exception.message)
    }
}