package Duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A Task that can be added to the Task List
 * which has a deadline date after the description.
 */

public class Deadline extends Task {

    protected LocalDate by;

    /**
     * A public constructor to create a Deadline Task.
     * @param description The description of the Deadline.
     * @param by The date of the Deadline.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of the deadline.
     * @return the string representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "--(by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }


    /**
     * Returns the string representation of the
     * deadline to be saved into the file.
     * @return the string representation of the
     *         deadline to be saved into the file.
     */
    @Override
    public String toStore() {
        return "[D]" + super.toString() + "--(by: " + by + ")";
    }
}
