import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A task
 */
public class Event extends Task {
    protected String dateNum;
    protected LocalDate date;

    /**
     * Constructor for event class
     * @param description task name for event
     * @param date date input for event timing
     */
    public Event(String description, String date) {
        super(description);

        this.dateNum = date;
        this.date = LocalDate.parse(date);
    }

    /**
     * Method to get string representation of the event type of task
     * @return string representing event task
     */
    @Override
    public String getTask() {
        return "E";
    }

    /**
     * Method to get string format of date
     * @return string format of date
     */
    public String getDate() {
        return "(at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Method to get numerical input format of date
     * @return numerical input format of date
     */
    public String getDateNum() {
        return dateNum;
    }
}
