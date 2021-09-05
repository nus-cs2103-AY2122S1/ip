package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Deadline class representing a task with a deadline.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructor for a Deadline object.
     *
     * @param desc The description of the task.
     * @param by The deadline of the task.
     * @throws DukeException If the deadline is not in yyyy-mm-dd format.
     */
    public Deadline(String desc, String by) throws DukeException {
        super(desc, 'D');
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("Deadline should be in the form yyyy-mm-dd"
                    + "\ni.e. deadline read book /by 2021-06-18");
        }
    }

    /**
     * A method that converts the task data into a suitable format to be saved in a save file.
     *
     * @return The formatted data as a string.
     */
    public String toData() {
        return super.toData() + "~S~" + by;
    }

    /**
     * Converts task data into string form to be displayed in the GUI.
     *
     * @return String format of data to be displayed in GUI.
     */
    @Override
    public String toString() {
        String byFormatted = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + byFormatted + ")";
    }
}
