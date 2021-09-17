package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class represents a Task with a deadline.
 */
public class Deadline extends Task {
    protected LocalDate byWhen;

    /**
     * Class constructor that receives a description of the Task and the deadline for the Task.
     *
     * @param description Description of the Task.
     * @param byWhen When the Task should be done by (deadline).
     */
    public Deadline(String description, String byWhen) {
        super(description);
        this.byWhen = LocalDate.parse(byWhen);
    }

    /**
     * Method which returns a String representation of the Deadline object to be displayed to the user.
     *
     * @return String Representation of the Deadline object to be displayed to the user.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by " + byWhen.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")"
                + " " + super.priorityString();
    }

    /**
     * Method which returns a String representation of the Deadline object to be written to data file.
     *
     * @return String Representation of the Deadline object to be written to data file.
     */
    @Override
    public String toFile() {
        return "D " + super.toFile() + "| " + this.byWhen;
    }
}
