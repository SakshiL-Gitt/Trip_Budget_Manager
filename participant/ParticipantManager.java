package participant;

import java.util.ArrayList;
import java.util.List;

public class ParticipantManager {

    private List<Participant> participants = new ArrayList<>();

    public void addParticipant(String name) {

        // Null and blank check
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Participant name cannot be empty");
        }

        name = name.trim();

        // Length validation
        if (name.length() < 3) {
            throw new IllegalArgumentException("Name must have at least 3 characters");
        }

        if (name.length() > 50) {
            throw new IllegalArgumentException("Name is too long");
        }

        // Duplicate check
        for (Participant p : participants) {
            if (p.getName().equalsIgnoreCase(name)) {
                throw new IllegalArgumentException("Participant already exists");
            }
        }

        participants.add(new Participant(name));
    }

    public List<Participant> getAllParticipants() {
        return new ArrayList<>(participants);   // Encapsulation safety
    }

    public int getParticipantCount() {
        return participants.size();
    }
}
