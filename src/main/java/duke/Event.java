package duke;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Event type of Task.
 * Events refer to a type of task that has a string as a description and a date of when the event is happening.
 */
public class Event extends Task {

    private String date;
    private DateTimeFormatter test;

    /**
     * Constructor for the Event class.
     * @param description description for the Event.
     * @param date date for the Event.
     */
    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    public Event(String description, String date, boolean status) {
        super(description, status);
        this.date = date;
    }

    /**
     * Returns a string form of the Event task.
     * @return returns the Event task in string form.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + date + ")";
    }
}
