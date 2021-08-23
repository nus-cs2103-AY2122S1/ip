import java.time.LocalDate;

/**
 * A Todo type task representation for Duke
 */
public class Event extends Task {

    protected LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + new Parser().formatLocalDate(at) + ")";
    }
}