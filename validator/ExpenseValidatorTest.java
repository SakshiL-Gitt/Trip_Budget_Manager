package validator;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import validator.ExpenseValidator;

import java.util.Arrays;
import java.util.List;

public class ExpenseValidatorTest {

    ExpenseValidator validator = new ExpenseValidator();

    @Test
    void testValidAmount() {
        assertTrue(validator.isValidAmount(500));
    }

    @Test
    void testInvalidAmount() {
        assertFalse(validator.isValidAmount(-10));
    }

    @Test
    void testValidCategory() {
        assertTrue(validator.isValidCategory("Food"));
    }

    @Test
    void testInvalidCategory() {
        assertFalse(validator.isValidCategory(""));
    }

    @Test
    void testParticipantExists() {
        List<String> list = Arrays.asList("A", "B");
        assertTrue(validator.participantExists("A", list));
    }

    @Test
    void testParticipantNotExists() {
        List<String> list = Arrays.asList("A", "B");
        assertFalse(validator.participantExists("C", list));
    }
}