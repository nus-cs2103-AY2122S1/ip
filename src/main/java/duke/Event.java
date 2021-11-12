package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * duke.Event class that extends task. Events should have a dead and time as well as a name
 */
public class Event extends Task {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private LocalDateTime dateTime;

    /**
     * Constructor for event class
     * @param name Name for the event
     * @param dateTime date the event is held
     */
    public Event(String name, LocalDateTime dateTime) {
        super(name);
        this.dateTime = dateTime;
    }

    /**
     * String representation of the event class
     * @return String. See above.
     */
    public String toString() {
        return "[E]" + super.toString() + " " + formatter.format(this.dateTime);
    }
}
