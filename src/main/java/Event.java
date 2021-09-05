
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Creates an Event, a type of Task with a date it is held at.
 */
public class Event extends Task {
    protected String stringAt;
    protected LocalDate dateAt;

//    public Event(String description, String at) {
//        super(description);
//        this.stringAt = at;
//    }

    /**
     * Constructor for an event class.
     * 
     * @param description a String which describes the task.
     * @param dateDueAt the LocalDate it is due at.
     */
    public Event(String description, LocalDate dateDueAt) {
        super(description);
        this.stringAt = dateDueAt.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        this.dateAt = dateDueAt;
    }

    @Override
    public String toString() {
        return "[Event]    " + super.toString() + " (at: " + stringAt + ")";
    }
}
