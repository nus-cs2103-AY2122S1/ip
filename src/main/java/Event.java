import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime at;
    
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }
    
    @Override
    public String taskList() {
        return "Event " + super.taskList() + "(at: " + at + ")";
    }
    
    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM d yyyy, h a");
        String formattedDtf = this.at.format(dtf);
        return "[E]" + super.toString() + " (at: " + formattedDtf + ")";
    }
}