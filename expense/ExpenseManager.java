package expense;

import java.util.ArrayList;
import java.util.List;

public class ExpenseManager {

    private List<Expense> expenses = new ArrayList<>();

    public void addExpense(String paidBy, double amount, String category) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        expenses.add(new Expense(paidBy, amount, category));
    }

    public List<Expense> getAllExpenses() {
        return expenses;
    }

    public double getTotalExpense() {
        double total = 0;
        for (Expense e : expenses) {
            total += e.getAmount();
        }
        return total;
    }
}



