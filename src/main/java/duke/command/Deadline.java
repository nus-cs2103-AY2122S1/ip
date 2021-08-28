package duke.command;

// import duke packages
import duke.DukeException;
import duke.task.Task;

// import java packages
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a task that has to be completed by a due date.
 * A <code>Deadline</code> consists of a description and a due date.
 */
public class Deadline extends Task {
    protected String byOriginal;
    protected LocalDate by;
    protected Date time;

    /**
     * Creates a Deadline task.
     *
     * @param description Description about the task.
     * @param by Due date of the task.
     * @throws DukeException If time is not formatted properly.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description, 'D');
        this.name = "deadline";
        byOriginal = by;

        String[] temp = by.split(" ", 2);
        try {
            this.by = LocalDate.parse(temp[0]);
        } catch (DateTimeParseException e) {
            throw new DukeException("time has to be in the format yyyy-mm-dd HHmm instead of " + by);
        }

        try {
            SimpleDateFormat parseFormat = new SimpleDateFormat("HHmm");
            this.time = parseFormat.parse(temp[1]);
        } catch (ParseException e) {
            throw new DukeException("time has to be in the format yyyy-mm-dd HHmm instead of " + by);
        }
    }

    /**
     * Converts due date to string format.
     *
     * @return Due date as a string.
     */
    protected String dateToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return formatter.format(by);
    }

    /**
     * Converts due time to string format.
     *
     * @return Due timing as a string.
     */
    protected String timeToString() {
        SimpleDateFormat displayFormat = new SimpleDateFormat("hh:mm a");
        return displayFormat.format(time);
    }

    /**
     * Gives due time in original format.
     *
     * @return Due timing in original form.
     */
    @Override
    public String getDueTime() {
        return byOriginal;
    }

    /**
     * Converts Deadline task to string format.
     *
     * @return Deadline as a string.
     */
    @Override
    public String toString() {
        return "[" + this.getCat() + "]" + super.toString() + " (by: " + dateToString() + " " + timeToString() + ")";
    }
}