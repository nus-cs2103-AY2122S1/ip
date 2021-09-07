package duke.task;

import duke.exception.DukeException;

/**
 * Represents deadlines that are tasks to be done by a certain date.
 *
 * @author botr99
 */
public class Deadline extends DateTask {
    /**
     * Constructs a deadline with the corresponding description,
     * and a string indicating the date to do the deadline by,
     * and that is by default not done.
     *
     * @param description Description of the deadline.
     * @param by String of the date.
     * @throws DukeException If the by string cannot be parsed into a date.
     */
    public Deadline(String description, String by) throws DukeException {
        this(description, by, false);
    }

    /**
     * Constructs a deadline with the corresponding description,
     * and a string indicating the date to do the deadline by,
     * and a boolean indicating whether the deadline is done.
     *
     * @param description Description of the deadline.
     * @param by String of the date.
     * @param isDone True if deadline is done; false otherwise.
     * @throws DukeException If the by string cannot be parsed into a date.
     */
    public Deadline(String description, String by, boolean isDone) throws DukeException {
        super(description, by, isDone);
    }

    /**
     * Returns a string indicating whether a deadline is done,
     * followed by the description of the deadline and the
     * formatted date of the deadline.
     *
     * @return The string representation of a deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDate() + ")";
    }

    /**
     * Returns a string indicating whether a deadline is done,
     * followed by the description of the deadline and the
     * string of the date that was passed in to the constructor.
     *
     * @return The string representation of a deadline to be stored in storage
     *         under the user's hard disk.
     */
    @Override
    public String toStorageString() {
        return "D | " + super.toStorageString() + " | " + getDateString();
    }
}
