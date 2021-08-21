import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate dateTime;
    public Deadline(String description, String dateTime) throws DukeException {
        super(description);
        this.dateTime = LocalDate.parse(dateTime);
    }
    public String toString() {
        return String.format("[D][%s] %s (by: %s)",
                getStatusIcon(), description, getDateTime());
    }

    public String getDateTime() {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public TaskType getType() {
        return TaskType.DEADLINE;
    }
}
