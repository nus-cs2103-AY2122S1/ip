/**
 * Class representing a Task with Deadline
 */
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate by;

    /**
     * Constructor of a Deadline, defaults to not completed.
     *
     * @param description Description of the Deadline.
     * @param by Due date of the Deadline
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor of a Deadline, defaults to not completed.
     *
     * @param description Description of the Deadline.
     * @param isCompleted If the Deadline is completed.
     * @param by Due date of the Deadline
     */
    public Deadline(String description, Boolean isCompleted, LocalDate by) {
        super(description, isCompleted);
        this.by = by;
    }

    /**
     * Returns the due date of the Deadline.
     *
     * @return Due date of the deadline.
     */
    public LocalDate getBy() {
        return this.by;
    }

    /**
     * Returns String representation of the Deadline.
     *
     * @return String representation of the Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns String representation of the Deadline in save format.
     *
     * @return String representation of the Deadline in save format.
     */
    @Override
    public String save() {
        return "D|" + (this.getStatus() ? "1" : "0") + "|" + this.getDescription() + "|" + this.getBy();
    }
}