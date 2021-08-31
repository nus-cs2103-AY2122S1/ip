package duke;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class representing a task with a do-by date
 */
public class Deadline extends Task {

    protected Date date;

    /**
     * Creates new deadline
     *
     * @param description Description of deadline
     * @param by By a certain date
     * @throws DukeException If date is in an incorrect format
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(by);
        } catch (ParseException e) {
            throw new DukeException("Date incorrect format");
        }
    }

    /**
     * Gives the string format of the deadline task
     *
     * @return String formatted
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + new SimpleDateFormat("dd/MM/yyyy").format(date) + ")";
    }
}
