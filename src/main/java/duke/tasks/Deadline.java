/**
 * This class encapsulates the Deadline Task.
 *
 * @author Megan Wee Rui En
 * @version CS2103T AY21/22 Semester 1
 */

package duke.tasks;

import java.time.LocalDate;

public class Deadline extends Task {
    private final LocalDate dateBy;

    public Deadline(String description, LocalDate dateBy) {
        super(description);
        this.dateBy = dateBy;
        this.isDone = false;
    }

    public Deadline(String description, LocalDate dateBy, boolean isDone) {
        super(description);
        this.dateBy = dateBy;
        this.isDone = isDone;
    }

    /**
     * Returns the string of the task to be represented in the list.
     *
     * @returns the string of the task to be represented in the list
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + description + " (by: " + dateBy.getDayOfMonth()
                + " " + dateBy.getMonth().toString() + " " + dateBy.getYear() + ")";
    }

    /**
     * Returns the string of the task to be represented in the text file.
     *
     * @returns the string of the task to be represented in the text file
     */
    @Override
    public String getStatusString() {
        return "D@" + (isDone ? 1 : 0) + "@" + this.description + "@" + this.dateBy.toString();
    }
}
