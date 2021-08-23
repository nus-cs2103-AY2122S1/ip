import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private String atString;
    private LocalDate atDate;
    public Event(String description, LocalDate at) {
        super(description);
        this.atDate = at;
    }

    public Event(String isDone, String description, String at) {
        super(description, isDone.equals("1"));
        this.atDate = LocalDate.parse(at);
    }
    
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + String.format(" (at: %s)", this.atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String toFileString() {
        return "E" + super.toFileString() + atDate;
    }
}