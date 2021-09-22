package duke;

/**
 * Represents the Deadline type of Task.
 * Deadlines refer to a type of task that has a string as a description and a deadline to be met.
 */
public class Deadline extends Task {

    private String deadline;

    /**
     * The constructor of the Deadline.
     *
     * @param description description of Deadline.
     * @param deadline deadline of Deadline.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, String deadline, boolean status) {
        super(description, status);
        this.deadline = deadline;
    }

    /**
     * Returns the deadline in string form.
     * @return Deadline in string form.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + deadline + ")";
    }
}
