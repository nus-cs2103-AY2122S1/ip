import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public LocalDate getTiming() {
        return this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " 
                + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

}