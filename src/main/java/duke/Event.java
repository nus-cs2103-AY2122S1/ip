package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Event task.
 */
public class Event extends Task {

    protected LocalDate at;

    /**
     * Class Constructor with the description and time of the task.
     *
     * @param description description of the task.
     * @param at time the task is at.
     */
    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at);
    }

    /**
     * Returns the String representation of a Event task.
     *
     * @return String representation of a Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns the String representation of a Event task for storage purpose.
     *
     * @return String representation of a Event task for storage purpose.
     */
    @Override
    public String toHistory() {
        return "E" + super.toHistory() + " | " + this.at;
    }
}
