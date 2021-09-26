package duke; /**
 * Class representing a duke.Task with duke.Deadline
 */
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate by;

    /**
     * Constructor of a duke.Deadline, defaults to not completed.
     *
     * @param description Description of the duke.Deadline.
     * @param by Due date of the duke.Deadline
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor of a duke.Deadline, defaults to not completed.
     *
     * @param description Description of the duke.Deadline.
     * @param isCompleted If the duke.Deadline is completed.
     * @param by Due date of the duke.Deadline
     */
    public Deadline(String description, Boolean isCompleted, LocalDate by) {
        super(description, isCompleted);
        this.by = by;
    }

    /**
     * Returns the due date of the duke.Deadline.
     *
     * @return Due date of the deadline.
     */
    public LocalDate getBy() {
        return this.by;
    }

    /**
     * Returns String representation of the duke.Deadline.
     *
     * @return String representation of the duke.Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns String representation of the duke.Deadline in save format.
     *
     * @return String representation of the duke.Deadline in save format.
     */
    @Override
    public String save() {
        return "D|" + (this.getStatus() ? "1" : "0") +
                "|" + this.getDescription() + "|" + this.getBy();
    }

    /**
     * Is the deadline due in the given number of days.
     * @param days Number of days.
     * @return If the deadline is due within the given number of days/
     */
    public Boolean isDueIn(int days) {
        return !this.getStatus() && LocalDate.now().plusDays(days).compareTo(by) >= 0;
    }
}