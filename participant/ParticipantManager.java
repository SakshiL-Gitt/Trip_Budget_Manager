package participant;

import java.util.ArrayList;
import java.util.List;

public class ParticipantManager {

    private List<Participant> participants = new ArrayList<>();

    public void addParticipant(String name) {
        for (Participant p : participants) {
            if (p.getName().equalsIgnoreCase(name)) {
                throw new IllegalArgumentException("Participant already exists");
            }
        }
        participants.add(new Participant(name));
    }

    public List<Participant> getAllParticipants() {
        return participants;
    }

    public int getParticipantCount() {
        return participants.size();
    }
}