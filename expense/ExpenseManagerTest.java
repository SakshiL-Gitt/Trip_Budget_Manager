package expense;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseManagerTest {

    private ExpenseManager manager;

    @BeforeEach
    void setUp() {
        manager = new ExpenseManager();
    }

    @Test
    void testAddExpenseValid() {
        manager.addExpense("Maya", 100.0, "Food");

        List<Expense> expenses = manager.getAllExpenses();
        assertEquals(1, expenses.size());
        assertEquals("Maya", expenses.get(0).getPaidBy());
        assertEquals(100.0, expenses.get(0).getAmount());
        assertEquals("Food", expenses.get(0).getCategory());
    }

    @Test
    void testAddMultipleExpenses() {
        manager.addExpense("Maya", 50.0, "Food");
        manager.addExpense("Sakshi", 75.0, "Transport");

        assertEquals(2, manager.getAllExpenses().size());
    }

    @Test
    void testGetTotalExpenseMultiple() {
        manager.addExpense("Arya", 50.0, "Food");
        manager.addExpense("Sanika", 75.0, "Transport");

        assertEquals(125.0, manager.getTotalExpense());
    }

    @Test
    void testGetTotalExpenseSingle() {
        manager.addExpense("Arya", 200.0, "Rent");

        assertEquals(200.0, manager.getTotalExpense());
    }

    @Test
    void testGetTotalExpenseEmpty() {
        assertEquals(0.0, manager.getTotalExpense());
    }

    @Test
    void testAddExpenseZeroAmount() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            manager.addExpense("Sanika", 0.0, "Misc");
        });

        assertEquals("Amount must be positive", exception.getMessage());
    }

    @Test
    void testAddExpenseNegativeAmount() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            manager.addExpense("Ritika", -50.0, "Refund");
        });

        assertEquals("Amount must be positive", exception.getMessage());
    }

    @Test
    void testDecimalPrecision() {
        manager.addExpense("Vaishnavi", 99.99, "Shopping");
        manager.addExpense("Diksha", 0.01, "Fees");

        assertEquals(100.0, manager.getTotalExpense(), 0.001);
    }

    @Test
    void testGetAllExpensesReturnsSameList() {
        manager.addExpense("Ritika", 30.0, "Snacks");

        List<Expense> expenses = manager.getAllExpenses();
        expenses.add(new Expense("Arya", 40.0, "Fuel"));

        // Since original list is returned directly, size should increase
        assertEquals(2, manager.getAllExpenses().size());
    }

    @Test
    void testAddExpenseWithNullValues() {
        manager.addExpense(null, 25.0, null);

        Expense expense = manager.getAllExpenses().get(0);
        assertNull(expense.getPaidBy());
        assertNull(expense.getCategory());
        assertEquals(25.0, expense.getAmount());
    }

    @Test
    void testLargeAmount() {
        manager.addExpense("abc", Double.MAX_VALUE, "Investment");

        assertEquals(Double.MAX_VALUE, manager.getTotalExpense());
    }
}
