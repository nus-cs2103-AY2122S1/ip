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
            throw new DukeException("Date format incorrect");
        }
    }

    /**
     * Gives the string format of the deadline task
     *
     * @return String formatted
     */
    @Override
    public String toString() {
        String s = "[D]" + super.toString() + " (by: " + new SimpleDateFormat("dd/MM/yyyy").format(date) + ")";
        if (this.tag != null) {
            s += " #" + this.tag;
        }
        return s;
    }

    /**
     * Returns save string for deadlines
     *
     * @return save string format
     */
    @Override
    public String getSaveString() {
        String s = "D" + this.getStatusIcon() + "_" + description + "_"
                + new SimpleDateFormat("dd/MM/yyyy").format(date);
        if (this.tag != null) {
            s += "_" + tag;
        }
        return s;
    }
}
