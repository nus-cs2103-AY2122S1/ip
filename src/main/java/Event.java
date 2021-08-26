import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    private LocalDateTime duration;

    Event(String taskName, LocalDateTime duration, boolean status) {
        super(taskName, status);
        this.duration = duration;
    }

    public String displayTask() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy h.mma");
        return "E " + super.displayTask() + "| " + duration.format(formatter);
    }
}
