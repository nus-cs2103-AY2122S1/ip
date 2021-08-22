import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDateTime timing;

    public Event(String task) {
        super(task.split(" /at ")[0]);
        this.timing = LocalDateTime.parse(task .split(" /at ")[1], DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"));
    }

    @Override
    public String getDescription() {
        return "[E] " + super.getDescription() + " (at: " + this.timing + ")";
    }


    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + this.timing + ")";
    }
}
