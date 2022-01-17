package calico.command;

// import java packages
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

// import duke packages
import calico.CalicoException;
import calico.task.Task;

/**
 * Represents a task that is about to happen on an upcoming date.
 * A <code>Event</code> consists of a description and a date.
 */
public class Event extends Task {
    protected String atOriginal;
    protected LocalDate at;
    protected Date time;

    /**
     * Creates an Event task.
     *
     * @param description Description about the task.
     * @param at Date of the task.
     * @throws CalicoException If time is not formatted properly.
     */
    public Event(String description, String at) throws CalicoException {
        super(description, 'E');
        this.name = "event";
        atOriginal = at;

        String[] temp = at.split(" ", 2);
        try {
            this.at = LocalDate.parse(temp[0]);
        } catch (DateTimeParseException e) {
            throw new CalicoException("time has to be in the format yyyy-mm-dd HHmm instead of " + at);
        }

        try {
            SimpleDateFormat parseFormat = new SimpleDateFormat("HHmm");
            this.time = parseFormat.parse(temp[1]);
        } catch (ParseException e) {
            throw new CalicoException("time has to be in the format yyyy-mm-dd HHmm instead of " + at);
        }
    }

    /**
     * Converts due date to string format.
     *
     * @return Due date as a string.
     */
    protected String dateToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return formatter.format(at);
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
        return atOriginal;
    }

    /**
     * Converts Event task to string format.
     *
     * @return Event as a string.
     */
    @Override
    public String toString() {
        return "[" + this.getCat() + "]" + super.toString() + " (at: " + dateToString() + " " + timeToString() + ")";
    }
}
