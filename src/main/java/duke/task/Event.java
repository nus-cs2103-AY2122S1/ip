package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Tasks that occur at a specified time.
 */
public class Event extends Task {
    private final LocalDate at;

    /**
     * Class constructor.
     *
     * @param description Description of the task.
     * @param at Time the task occurs.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Alternative constructor for initializing from storage.
     *
     * @param isDone Whether the task is already checked.
     * @param description Description of the task.
     * @param at Time the task occurs.
     */
    public Event(boolean isDone, String description, LocalDate at) {
        super(isDone, description);
        this.at = at;
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return String representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + " (at: "
                + at.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }

    /**
     * Returns a string representation of the event to be saved as.
     *
     * @return String representation of the event to be saved as.
     */
    @Override
    public String saveString() {
        return "E|" + super.saveString() + "|" + at;
    }
}
