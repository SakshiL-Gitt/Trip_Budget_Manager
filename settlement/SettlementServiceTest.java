package settlement;

import expense.Expense;
import participant.Participant;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SettlementServiceTest {

    private final SettlementService service = new SettlementService();

    
    private Participant participant(String name) {
        return new Participant(name);
    }

    private Expense expense(String paidBy, double amount,String category) {
        return new Expense(paidBy, amount,category);
    }

    @Test
    void testEqualSplit() {
        List<Participant> participants = Arrays.asList(
                participant("A"),
                participant("B")
        );

        List<Expense> expenses = Arrays.asList(
                expense("A", 100, "Medicine")
        );

        Map<String, Double> result =
                service.calculateBalances(participants, expenses);

        assertEquals(50.0, result.get("A"), 0.001);
        assertEquals(-50.0, result.get("B"), 0.001);
    }

   
    @Test
    void testMultipleExpenses() {
        List<Participant> participants = Arrays.asList(
                participant("A"),
                participant("B"),
                participant("C")
        );

        List<Expense> expenses = Arrays.asList(
                expense("A", 90, "Fruits"),
                expense("B", 60, "Milk")
        );

        Map<String, Double> result =
                service.calculateBalances(participants, expenses);

        
        assertEquals(40.0, result.get("A"), 0.001);
        assertEquals(10.0, result.get("B"), 0.001);
        assertEquals(-50.0, result.get("C"), 0.001);
    }

    @Test
    void testNoExpenses() {
        List<Participant> participants = Arrays.asList(
                participant("A"),
                participant("B")
        );

        List<Expense> expenses = Collections.emptyList();

        Map<String, Double> result =
                service.calculateBalances(participants, expenses);

        assertEquals(0.0, result.get("A"), 0.001);
        assertEquals(0.0, result.get("B"), 0.001);
    }

    
    @Test
    void testSingleParticipant() {
        List<Participant> participants =
                Collections.singletonList(participant("A"));

        List<Expense> expenses = Arrays.asList(
                expense("A", 200, "Snacks")
        );

        Map<String, Double> result =
                service.calculateBalances(participants, expenses);

        assertEquals(0.0, result.get("A"), 0.001);
    }














    

    @Test
    void testFloatingPointPrecision() {
        List<Participant> participants = Arrays.asList(
                participant("A"),
                participant("B"),
                participant("C")
        );

        List<Expense> expenses = Arrays.asList(
                expense("A", 100,"Ticket")
        );

        Map<String, Double> result =
                service.calculateBalances(participants, expenses);

        assertEquals(66.666, result.get("A"), 0.01);
        assertEquals(-33.333, result.get("B"), 0.01);
        assertEquals(-33.333, result.get("C"), 0.01);
    }

    @Test
    void testExpensePaidByUnknownParticipant() {
        List<Participant> participants = Arrays.asList(
                participant("A"),
                participant("B")
        );

        List<Expense> expenses = Arrays.asList(
                expense("C", 100,"Breakfast")   // not in participants
        );

        assertThrows(NullPointerException.class, () ->
                service.calculateBalances(participants, expenses));
    }
}

