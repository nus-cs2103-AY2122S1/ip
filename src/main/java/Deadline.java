public class Deadline extends Task{
    private final String dateOfDeadline;

    public Deadline(String description, String dateOfDeadline) {
        super(description, "D");
        this.dateOfDeadline = dateOfDeadline;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (by: %s)", dateOfDeadline); // No preposition
    }
}
