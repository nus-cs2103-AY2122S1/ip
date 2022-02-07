package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event class with time under task.
 */
public class Event extends Task {
    protected LocalDate time;

    /**
     * Constructor for Event.
     *
     * @param description Name of the event.
     * @param time Time of the event.
     */
    public Event(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    /**
     * String representation of a event.
     *
     * @return String to be displayed.
     */
    @Override
    public String toString() {
        return ("E "
                + super.toString()
                + " / "
                + time.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
            );
    }

    /**
     * String format of an event that will be stored to the file.
     *
     * @return String to be stored.
     */
    @Override
    public String toStoredString() {
        return ("E "
                + super.toString()
                + " / "
                + time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            );
    }
}
