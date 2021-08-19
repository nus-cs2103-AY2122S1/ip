package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for Event type Task objects.
 */

public class Event extends Task{
    private final LocalDate date;

    /**
     * Basic constructor for Event Task objects.
     * @param label description of the Task.
     * @param date date associated with the Task.
     */
    public Event(String label, LocalDate date) {
        this.date = date;
        this.label = label;
    }

    /**
     * Getter for type.
     * @return "E" as an identifier for other parts of the application.
     */
    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String getDate() {
        return date.toString();
    }
}
