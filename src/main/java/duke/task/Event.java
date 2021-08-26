package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task {

    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    
    public Event(String description, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.isDone = isDone;
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + (this.isDone ? "[X] " : "[ ] ") + this.description 
                + " (from: " 
                + this.from.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM,FormatStyle.SHORT)) 
                + " to "
                + this.to.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM,FormatStyle.SHORT))
                + ")";
    }

    @Override
    public String toStorage() {
        return ("E%" + this.isDone + "%" + this.description + "%" + this.from + "%" + this.to + "\n");
    }
}
