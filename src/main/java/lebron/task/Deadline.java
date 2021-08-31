package lebron.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class represents the Deadline event.
 *
 * @author Nigel Tan
 */
public class Deadline extends Task {

    private LocalDate by;

    /**
     * Constructor.
     *
     * @param description the name of the task
     * @param by the deadline
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }

    @Override
    public String toFile() {
        return "D | " + super.getDoneValue() + " | " + super.getName() + " | " + this.by;
    }
}
