package reportService;

import expense.Expense;
import participant.Participant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportService {

    public void generateReport(List<Participant> participants,
                               List<Expense> expenses) {

        System.out.println("\n----- Trip Summary -----");

        System.out.println("Total Participants: " + participants.size());

        double totalExpense = 0;
        Map<String, Double> categoryMap = new HashMap<>();
        Map<String, Double> spenderMap = new HashMap<>();

        for (Expense e : expenses) {
            totalExpense += e.getAmount();

            // Category-wise total
            categoryMap.put(
                    e.getCategory(),
                    categoryMap.getOrDefault(e.getCategory(), 0.0) + e.getAmount()
            );

            // Spender-wise total
            spenderMap.put(
                    e.getPaidBy(),
                    spenderMap.getOrDefault(e.getPaidBy(), 0.0) + e.getAmount()
            );
        }

        System.out.println("Total Expense: ₹" + totalExpense);

        if (participants.size() > 0) {
            System.out.println("Average per person: ₹" +
                    (totalExpense / participants.size()));
        }

        System.out.println("\nCategory Breakdown:");
        for (String category : categoryMap.keySet()) {
            System.out.println(category + ": ₹" + categoryMap.get(category));
        }

        String highestSpender = "";
        double maxAmount = 0;

        for (String person : spenderMap.keySet()) {
            if (spenderMap.get(person) > maxAmount) {
                maxAmount = spenderMap.get(person);
                highestSpender = person;
            }
        }

        if (!highestSpender.isEmpty()) {
            System.out.println("\nHighest Spender: " +
                    highestSpender + " (₹" + maxAmount + ")");
        }
    }
}
