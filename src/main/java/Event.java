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

    public Event(String description, String dateTime, boolean isDone) {
        super(description, isDone);
        this.dateTime = dateTime;
    }

    @Override
    public String getFormattedData() {
        return super.getFormattedData() + "|" + this.dateTime;
    }

    @Override
    public String getTaskIdentifier() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + this.description + " (at: " + this.dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
