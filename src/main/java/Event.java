import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    /**
     * String representation of date of Event.
     */
    protected LocalDate date;
    protected String time;

    /**
     * Constructor for Event object.
     * @param description Description of Event.
     */
    public Event(String description, LocalDate ld, String time) {
        super(description);
        this.date = ld;
        this.time = time;
        this.type = TaskType.EVENT;
    }

    /**
     * Returns string representation of Event object.
     * @return String representation of Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " +  this.date.format(DateTimeFormatter.ofPattern("d MMM yyyy ")) + this.time + ")";
    }
}
