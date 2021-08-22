import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate atDate;
    protected LocalTime atTime;

    public Event(String description, LocalDate atDate) {
        super(description);
        this.atDate = atDate;
    }

    public Event(String description, LocalDate atDate, LocalTime atTime) {
        super(description);
        this.atDate = atDate;
        this.atTime = atTime;
    }

    @Override
    public String toString() {
        if (atTime != null) {
            return "[E]" + super.toString() + " (at: "
                    + atDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                    + ", " + atTime + ")";
        }
        return "[E]" + super.toString() + " (at: "
                + atDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }
}
