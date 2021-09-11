package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** Child class that extends parent 'Task' class and handles
 * operations for the Deadline task.
 */
public class Deadline extends Task {
    protected LocalDate by;
    private String dateForObject;

    /**
     * Constructor for the Deadline class.
     *
     * @param description The description of the task
     * @param by The date which is the deadline of the task
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
        this.dateForObject = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * toString() method for deadline object
     *
     * @return String The task printed in the correct format
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " by: " + this.by;
    }
}