package duke;

import java.time.LocalDate;

/**
 * Represents a specific type of Task with an additional time field.
 */
public class Event extends Task {
    private LocalDate time;

    /**
     * The constructor for an Event Object.
     */
    public Event(String taskTitle, LocalDate time) {
        super(taskTitle);
        this.time = time;
    }

    /**
     * Customizes the string representation of an event object.
     *
     * @return string representation of an event in the form [E][{X}] {description} (at: {time})
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + time.getMonth().toString() + " " +
                time.getDayOfMonth() + " " + time.getYear() + ")";
    }
}
