package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDateTime time;
    
    public Event(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }
    
    public LocalDateTime getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mma")) + ")";
    }
}