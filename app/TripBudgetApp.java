package app;
import expense.ExpenseManager;
import participant.ParticipantManager;
import settlement.SettlementService;

import java.util.Map;
import java.util.Scanner;

public class TripBudgetApp {

    private ParticipantManager participantManager;
    private ExpenseManager expenseManager;
    private SettlementService settlementService;
    private Scanner sc;

    public TripBudgetApp() {
        participantManager = new ParticipantManager();
        expenseManager = new ExpenseManager();
        settlementService = new SettlementService();
        sc = new Scanner(System.in);
    }

    public void start() {

        while (true) {
            printMenu();
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addParticipant();
                    break;

                case 2:
                    addExpense();
                    break;

                case 3:
                    showTotalExpense();
                    break;

                case 4:
                    showSettlement();
                    break;

                case 5:
                    exitApp();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n--- Trip Budget Manager ---");
        System.out.println("1. Add Participant");
        System.out.println("2. Add Expense");
        System.out.println("3. Show Total Expense");
        System.out.println("4. Show Settlement");
        System.out.println("5. Exit");
        System.out.print("Choose option: ");
    }

    private void addParticipant() {
        System.out.print("Enter participant name: ");
        String name = sc.nextLine();
        participantManager.addParticipant(name);
        System.out.println("Participant added successfully.");
    }

    private void addExpense() {
        System.out.print("Paid by: ");
        String paidBy = sc.nextLine();

        System.out.print("Amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        System.out.print("Category: ");
        String category = sc.nextLine();

        expenseManager.addExpense(paidBy, amount, category);
        System.out.println("Expense added successfully.");
    }

    private void showTotalExpense() {
        System.out.println("Total Expense: ₹" + expenseManager.getTotalExpense());
    }

    private void showSettlement() {
        Map<String, Double> balances =
                settlementService.calculateBalances(
                        participantManager.getAllParticipants(),
                        expenseManager.getAllExpenses()
                );

        System.out.println("\n--- Settlement ---");
        for (String person : balances.keySet()) {
            System.out.println(person + " : " + balances.get(person));
        }
    }

    private void exitApp() {
        System.out.println("Thank you for using Trip Budget Manager!");
        sc.close();
    }
}
