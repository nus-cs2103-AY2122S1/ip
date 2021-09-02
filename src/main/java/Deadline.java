/**
 * A Task with a deadline.
 * @author Thomas Hogben
 */
public class Deadline extends DateAndTimeTask {
    private String deadline;

    /**
     * @param description The description of the Task.
     */
    public Deadline(String description) {
        super(description);
    }

    /**
     * @param description The description of the Task.
     * @param details The deadline of the Task.
     */
    public Deadline(String description, String details) throws DukeException {
        super(description, details);
    }

    public Deadline(String description, String deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

        @Override
    public String getSave() {
        return "D" +
                (this.isDone() ? "1" : "0") + "|" +
                this.getDescription() + "|" +
                this.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
