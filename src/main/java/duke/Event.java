package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * An Event task that extends from Task.
 *
 * @author Erwin Quek
 * @version CS2103 AY21/22 Sem 1
 */
public class Event extends Task{
    private String dateTime;
    private final LocalDateTime dt;

    /**
     * A constructor for Event
     * @param description Takes in a user input
     */
    public Event(String description) {
        super(description.substring(0, description.indexOf("/at ")).trim());
        this.dateTime = description.substring(description.indexOf("/at ") + 4).trim();
        this.dt = LocalDateTime.parse(this.dateTime);
    }

    /**
     * A method to get event task info.
     * @return a string of the deadline task details to be saved in data
     */
    @Override
    public String getTaskInfo() {
        return "E" + "|" + super.getZeroOrOne() + "|" + description + "|" + dateTime;
    }

    /**
     * A method to get a String representation of Event task.
     * @return a string of the Deadline task.
     */
    @Override
    public String toString() {
        String formatDate = dt.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String formatTime = dt.format(DateTimeFormatter.ofPattern("hh:mm"));
        return "[E]" + super.toString() + "(at: " + formatDate +  ", " + formatTime + ")";
    }
}