package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime at;
    private String formattedDtf;
    
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM d yyyy, h a");
        String formattedDtf = this.at.format(dtf);
        this.formattedDtf = formattedDtf;
    }

    public String getTaskListOnDisk() {
        return "duke.task.Event |" + super.getStatusIcon() + "| " + description + " | by: " + formattedDtf;
    }
    
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + formattedDtf + ")";
    }
}