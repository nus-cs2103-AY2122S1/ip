/**
 * The Deadline Class represents a task that needs to be done before a specific date/time.
 *
 * It contains information relating to the task:
 * - description
 * - isDone
 * - dueDate
 *
 * @author Benedict Chua
 */
public class Deadline extends Task {
    private String dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.dueDate);
    }
}