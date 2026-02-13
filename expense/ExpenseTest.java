package expense;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExpenseTest {

    @Test
    void testConstructorAndGetters() {
        Expense expense = new Expense("Maya", 100.50, "Food");

        assertEquals("Maya", expense.getPaidBy());
        assertEquals(100.50, expense.getAmount());
        assertEquals("Food", expense.getCategory());
    }
    @Test
    void testZeroAmount() {
        Expense expense = new Expense("Sakshi", 0.0, "Transport");

        assertEquals(0.0, expense.getAmount());
    }

    @Test
    void testNegativeAmount() {
        Expense expense = new Expense("Arya", -50.0, "Refund");

        assertEquals(-50.0, expense.getAmount());
    }

    @Test
    void testNullPaidBy() {
        Expense expense = new Expense(null, 20.0, "Misc");

        assertNull(expense.getPaidBy());
    }

    @Test
    void testNullCategory() {
        Expense expense = new Expense("Sanika", 75.0, null);

        assertNull(expense.getCategory());
    }

    @Test
    void testEmptyPaidBy() {
        Expense expense = new Expense("", 30.0, "Bills");

        assertEquals("", expense.getPaidBy());
    }

    @Test
    void testEmptyCategory() {
        Expense expense = new Expense("Ritika", 40.0, "");

        assertEquals("", expense.getCategory());
    }

    @Test
    void testLargeAmount() {
        Expense expense = new Expense("Diksha", Double.MAX_VALUE, "Investment");

        assertEquals(Double.MAX_VALUE, expense.getAmount());
    }

    @Test
    void testDecimalPrecision() {
        Expense expense = new Expense("Vaishnavai", 99.999, "Shopping");

        assertEquals(99.999, expense.getAmount(), 0.001);
    }

    @Test
    void testMultipleObjectsIndependence() {
        Expense expense1 = new Expense("Maya", 10.0, "Snacks");
        Expense expense2 = new Expense("Sakshi", 20.0, "Fuel");

        assertNotEquals(expense1.getPaidBy(), expense2.getPaidBy());
        assertNotEquals(expense1.getAmount(), expense2.getAmount());
        assertNotEquals(expense1.getCategory(), expense2.getCategory());
    }

    @Test
    void testSpecialCharacters() {
        Expense expense = new Expense("abc@123", 55.5, "Food & Drinks");

        assertEquals("abc@123", expense.getPaidBy());
        assertEquals("Food & Drinks", expense.getCategory());
    }
}
