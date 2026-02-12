package participant;

public class Participant {
    private String name;

    public Participant(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Participant name cannot be empty");
        }

        if (!name.matches("[A-Za-z ]+")) {
            throw new IllegalArgumentException("Name must contain only alphabets and spaces");
        }

        this.name = name.trim();
    }

    public String getName() {
        return name;
    }
}
