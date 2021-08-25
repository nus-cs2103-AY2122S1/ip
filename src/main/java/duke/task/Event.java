package duke.task;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime at;

    public Event(String detail, LocalDateTime at) {
        super(detail,"E");
        this.at = at;
    }

    public LocalDateTime getTime() {
        return at;
    }

    @Override
    public String toString() {
        String time = at.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mma"));
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
