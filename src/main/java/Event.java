import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


/**
 * A task that is scheduled for a specific time.
 */
public class Event extends Task{
    protected LocalDate date;
    protected LocalTime time;

    public Event(String description, String dateString, String timeString) {
        super(description);
        this.date = LocalDate.parse(dateString);
        if (timeString == "") {
            this.time = null;
        } else {
            this.time = LocalTime.parse(timeString);
        }
    }

    public Event(String description, String dateString) {
        this(description, dateString, "");
    }

    /**
     * Provides a String representation of the Deadline.
     * @return A String representation of the Deadline.
     */
    public String toString() {
        if (this.time != null) {
            return "[E][" + this.getStatusIcon() + "] " + this.description + "(by: " +
                    this.date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)) + " " +
                    this.time.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)) + ")";
        } else {
            return "[E][" + this.getStatusIcon() + "] " + this.description + "(by: " +
                    this.date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)) + ")";
        }
    }
}
