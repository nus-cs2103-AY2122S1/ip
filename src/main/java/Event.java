import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Creates an Event, a type of Task with a date it is held at.
 */
public class Event extends Task {
    protected String stringAt;
    protected LocalDate dateAt;

    public Event(){
        super();
    }
    /**
     * Constructor for an event class.
     * 
     * @param description a String which describes the task.
     * @param dateAt the LocalDate it is due at.
     */
    public Event(String description, LocalDate dateAt) {
        super(description);
        this.stringAt = dateAt.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        this.dateAt = dateAt;
    }
    
    // getters & setters (needed for jackson)
    protected void setDateDueAt(LocalDate dateDueAt) {
        this.dateAt = dateDueAt;
    }

    protected void setStringAt(String stringAt) {
        this.stringAt = stringAt;
    }

    @Override
    public String toString() {
        return "[Event]    " + super.toString() + " (at: " + stringAt + ")";
    }
}
