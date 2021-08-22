import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    private final LocalDateTime timing;

    public Event(String task) throws DukeException {
        super(task.split(" /at ")[0]);
        try {
            this.timing = LocalDateTime.parse(task.split(" /at ")[1], DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"));
        } catch (DateTimeParseException ex) {
            throw new DukeException("Ensure that date time is of the format d/M/yyyy HH:mm");
        }
    }

    private String getDateTimeFormatted() {
        return this.timing.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
    }

    @Override
    public String getDescription() {
        return "[E] " + super.getDescription() + " (at: " + this.getDateTimeFormatted() + ")";
    }

    public LocalDateTime getTiming() {
        return timing;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + this.getDateTimeFormatted() + ")";
    }

    @Override
    public int compareTo(Task o) {
        int val = super.compareTo(o);
        if (val == 0) {
            if (o instanceof Event) {
                return this.timing.compareTo(((Event) o).timing);
            } else if (o instanceof Deadline) {
                return this.timing.compareTo(((Deadline) o).getDeadline());
            } else {
                return this.getDescription().compareTo(o.getDescription());
            }
        } else {
            return val;
        }
    }
}
