package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {

    private LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + (this.isDone ? "[X] " : "[ ] ") + this.description + " (by: " 
                + this.by.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM,FormatStyle.SHORT)) 
                + ")";
    }
}
