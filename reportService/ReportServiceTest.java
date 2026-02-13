package reportService;

import expense.Expense;
import org.junit.jupiter.api.Test;
import participant.Participant;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReportServiceTest {

    @Test
    void testGenerateReportOutput() {

        // Arrange
        List<Participant> participants = List.of(
                new Participant("A"),
                new Participant("B")
        );

        List<Expense> expenses = List.of(
                new Expense("A", 1000, "Food"),
                new Expense("B", 2000, "Travel")
        );

        ReportService service = new ReportService();

        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Act
        service.generateReport(participants, expenses);

        // Convert output to string
        String output = outputStream.toString();

        // Assert important parts exist
        assertTrue(output.contains("Total Participants: 2"));
        assertTrue(output.contains("Total Expense: ₹3000.0"));
        assertTrue(output.contains("Average per person: ₹1500.0"));
        assertTrue(output.contains("Food: ₹1000.0"));
        assertTrue(output.contains("Travel: ₹2000.0"));
        assertTrue(output.contains("Highest Spender: B (₹2000.0)"));
    }
}
