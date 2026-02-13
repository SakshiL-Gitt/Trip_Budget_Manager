package participant;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParticipantManagerTest {

    private ParticipantManager manager;

    @BeforeEach
    void setup() {
        manager = new ParticipantManager();
    }

    @Test
    void testAddValidParticipant() {
        manager.addParticipant("Arya");
        assertEquals(1, manager.getParticipantCount());
    }

    @Test
    void testAddParticipantTrimmed() {
        manager.addParticipant("   Arya   ");
        assertEquals(1, manager.getParticipantCount());
        assertEquals("Arya", manager.getAllParticipants().get(0).getName());
    }

    @Test
    void testDuplicateParticipant() {
        manager.addParticipant("Arya");

        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            manager.addParticipant("arya");
        });

        assertEquals("Participant already exists", e.getMessage());
    }

    @Test
    void testNullName() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            manager.addParticipant(null);
        });

        assertEquals("Participant name cannot be empty", e.getMessage());
    }

    @Test
    void testEmptyName() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            manager.addParticipant("   ");
        });

        assertEquals("Participant name cannot be empty", e.getMessage());
    }

    @Test
    void testShortName() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            manager.addParticipant("Al");
        });

        assertEquals("Name must have at least 3 characters", e.getMessage());
    }

    @Test
    void testVeryLongName() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 60; i++) {
            sb.append("A");
        }
        String longName = sb.toString();

        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            manager.addParticipant(longName);
        });

        assertEquals("Name is too long", e.getMessage());
    }

    @Test
    void testGetAllParticipantsReturnsCopy() {
        manager.addParticipant("Arya");

        List<Participant> list = manager.getAllParticipants();
        list.clear(); // modify returned list

        // Original list should not be affected
        assertEquals(1, manager.getParticipantCount());
    }

    @Test
    void testMultipleParticipants() {
        manager.addParticipant("Arya");
        manager.addParticipant("Sanika");
        manager.addParticipant("Maya");

        assertEquals(3, manager.getParticipantCount());
    }

}
