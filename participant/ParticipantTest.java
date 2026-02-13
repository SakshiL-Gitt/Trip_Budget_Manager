package participant;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ParticipantTest {

    @Test
    void testValidName() {
        Participant p = new Participant("Arya");
        assertEquals("Arya", p.getName());
    }

    @Test
    void testNameWithSpaces() {
        Participant p = new Participant("Arya Patil");
        assertEquals("Arya Patil", p.getName());
    }

    @Test
    void testNameTrimmed() {
        Participant p = new Participant("   Arya   ");
        assertEquals("Arya", p.getName());
    }

    @Test
    void testNullName() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new Participant(null);
        });
        assertEquals("Participant name cannot be empty", e.getMessage());
    }

    @Test
    void testEmptyName() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new Participant("   ");
        });
        assertEquals("Participant name cannot be empty", e.getMessage());
    }

    @Test
    void testNameWithNumbers() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new Participant("Arya123");
        });
        assertEquals("Name must contain only alphabets and spaces", e.getMessage());
    }

    @Test
    void testNameWithSpecialChars() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new Participant("Arya@");
        });
        assertEquals("Name must contain only alphabets and spaces", e.getMessage());
    }

}
