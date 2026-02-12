package settlement;

import expense.Expense;
import participant.Participant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettlementService {

    public Map<String, Double> calculateBalances(
            List<Participant> participants,
            List<Expense> expenses) {

        Map<String, Double> balanceMap = new HashMap<>();

        for (Participant p : participants) {
            balanceMap.put(p.getName(), 0.0);
        }

        double totalExpense = 0;
        for (Expense e : expenses) {
            totalExpense += e.getAmount();
            balanceMap.put(
                    e.getPaidBy(),
                    balanceMap.get(e.getPaidBy()) + e.getAmount()
            );
        }

        double share = totalExpense / participants.size();

        for (String name : balanceMap.keySet()) {
            balanceMap.put(name, balanceMap.get(name) - share);
        }

        return balanceMap;
    }
}

