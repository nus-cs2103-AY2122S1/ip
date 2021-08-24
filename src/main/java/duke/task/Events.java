package duke.task;

import duke.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    protected String at;
    protected LocalDateTime localDateTime;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public Events(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the event's date and time
     *
     * @return The event date and time.
     */
    public String getAt() {
        return at;
    }

    /**
     * Returns the formatted string representation of the task.
     *
     * @return String representation of task.
     */
    @Override
    public String toString() {
        localDateTime = new Parser().parseLocalDateTime(at);
        return "[E]" + super.toString() + " (at: " + localDateTime.format(dtf) + ")";
    }

    /**
     * Prints out event task.
     */
    @Override
    public void displayTask() {
        System.out.println(toString());

    }
}
