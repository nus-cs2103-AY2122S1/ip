import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDate dateTime;

    public Event(String description, String dateTime) {
        super(description);
        this.dateTime = LocalDate.parse(dateTime);
    }
    @Override
    public boolean hasDueDate(LocalDate dueDate) {
        return dueDate.isEqual(this.dateTime);
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + this.description + " (at: " + this.dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
