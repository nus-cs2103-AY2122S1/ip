import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate dateTime;
    public Event(String description, String dateTime) throws DukeException {
        super(description);
        this.dateTime = LocalDate.parse(dateTime);
    }
    public String toString() {
        return String.format("[E][%s] %s (at: %s)",
                getStatusIcon(), description, getDateTime());
    }

    public String getDateTime() {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public TaskType getType() {
        return TaskType.EVENT;
    }
}
