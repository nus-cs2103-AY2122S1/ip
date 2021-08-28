import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDate at;
    private String dateForObject;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
        this.dateForObject = at.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.dateForObject + ")";
    }
}
