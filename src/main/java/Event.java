import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private final LocalDate date;

    public Event(String label, LocalDate date) {
        this.date = date;
        this.label = label;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                "(at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
