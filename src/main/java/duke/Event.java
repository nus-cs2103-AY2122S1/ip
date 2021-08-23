package duke;

import java.time.LocalDate;

/**
 * Class for Event tasks.
 */
public class Event extends Task {
    private LocalDate time;

    /**
     * Constructor for Event object.
     * @param description String representing description of the event.
     * @param time LocalDate of the time.
     */
    public Event(String description, LocalDate time) {
        super(description);
        this.time = time;
    };

    /**
     * Getter for time.
     * @return LocalDate representing the time.
     */
    public LocalDate getTime() {
        return this.time;
    }
}
