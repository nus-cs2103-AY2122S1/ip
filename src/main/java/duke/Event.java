package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**Child class that extends parent 'Task' class and handles
 * operations for the Event task.
 * */
public class Event extends Task{
    protected LocalDate at;
    private String dateForObject;

    /**
     * Constructor for the Event class.
     *
     * @param description The description of the task
     * @param at The date on which the task must be executed
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
        this.dateForObject = at.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " at: " + this.at;
    }
}
