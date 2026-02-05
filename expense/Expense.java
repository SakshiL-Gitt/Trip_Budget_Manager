package expense;

public class Expense {

    private String paidBy;
    private double amount;
    private String category;

    public Expense(String paidBy, double amount, String category) {
        this.paidBy = paidBy;
        this.amount = amount;
        this.category = category;
    }

    public String getPaidBy() {
        return paidBy;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }
}
