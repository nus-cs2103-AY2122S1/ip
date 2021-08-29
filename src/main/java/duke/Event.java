package duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * An Event class that inherits from Task and includes a time for the Task.
 */

public class Event extends Task {

    private final LocalDateTime date;

    /**
     * Constructor.
     * @param title title of the task.
     * @param date time of the task.
     */
    public Event(String title, LocalDateTime date) {
        super(title);
        this.date = date;
    }

    /**
     * toString method.
     * @return a String.
     */
    @Override
    public String toString() {
        if (!this.getDone()) {
            return "[E][ ]" + this.getTitle()
                    + "| (at: " + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
        } else {
            return "[E][X]" + this.getTitle()
                    + "| (at: " + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
        }

    }


}
