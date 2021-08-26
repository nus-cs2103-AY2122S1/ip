import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Event (Task). Can be added to list in Duke.
 *
 * @author Ruth Poh (Lab 10H)
 */
public class Event extends Task {
    protected String at;

    /**
     * Constructor to initialize Deadline.
     *
     * @param taskstr Task.
     * @param at Date/Time of task.
     */
    public Event(String taskstr, String at) {
        super(taskstr);
        this.at = at;
    }

    @Override
    public String getTime() {
        return this.at;
    }

    /**
     * Returns string of Deadline (Task).
     * @return string of Deadline.
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + "(at: " + this.at + ")";
    }
}
