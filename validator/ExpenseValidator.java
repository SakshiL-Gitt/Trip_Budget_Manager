package validator;

import java.util.List;

public class ExpenseValidator {

    public boolean isValidAmount(double amount) {
        return amount > 0;
    }

    public boolean isValidCategory(String category) {
        return category != null && !category.trim().isEmpty();
    }

    public boolean participantExists(String name, List<String> participants) {
        return participants.contains(name);
    }
}