package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a class that extends Task with additional variables to store the date.
 *
 * @author meerian
 */
public class Deadline extends Task {
    /**
     * Represents the date when the event occurs. Only one field is 'filled' in each
     * event object.
     */
    private String when;
    private LocalDate time = null;

    /**
     * Creates a deadline with the specified description.
     *
     * @param str1 The deadline's description.
     * @param str2 The deadline's date in a string
     * @throws DukeException when the user inputs an empty description.
     */
    public Deadline(String str1, String str2) throws DukeException {
        super(str1);
        this.when = str2;
    }

    /**
     * Creates a deadline with the specified description.
     *
     * @param str1 The deadline's description.
     * @param time The deadline's date in a localDate
     * @throws DukeException when the user inputs an empty description.
     */
    public Deadline(String str1, LocalDate time) throws DukeException {
        super(str1);
        this.time = time;
    }

    /**
     * Returns the string representation of the deadline to be displayed based on whether
     * the object is initialized with a localDate or String.
     *
     * @return the string representation of the deadline.
     */
    @Override
    public String toString() {
        if (time == null) {
            return "[D]" + super.toString() + " (by: " + when + ")";
        } else {
            return "[D]" + super.toString() + " (by: "
                + time.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        }
    }
}
