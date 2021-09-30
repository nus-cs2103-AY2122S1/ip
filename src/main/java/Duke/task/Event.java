package Duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Event extends Task {

    protected String type;
    protected LocalDate time;

    public Event(String description, LocalDate time) {
        super(description);
        this.type = "Event";
        this.time = time;
    }

    /**
     * Returns the string representation of the event task in the text file
     * @return string representation of the event task in the text file
     */
    public String addToFile() {
        int status = super.isDone == true ? 1 : 0;
        return "E | " +  status + " | " + this.description + " | " + this.time;
    }

    /**
     * Returns the string representation of the task to be shown in the Duke app
     * @return the string representation of the task to be shown in the Duke app
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}