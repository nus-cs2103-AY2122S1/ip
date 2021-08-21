/**
 * A deadline is a task that has a specific deadline.
 */
public class Deadline extends Task{
    private final String dateOfDeadline;

    /**
     * Creates a new deadline object that has the given description and due on the given date.
     *
     * @param description The description of the deadline
     * @param dateOfDeadline The due date/time of the task
     */
    public Deadline(String description, String dateOfDeadline) {
        super(description, "D");
        this.dateOfDeadline = dateOfDeadline;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (by: %s)", dateOfDeadline); // No preposition
    }
}
