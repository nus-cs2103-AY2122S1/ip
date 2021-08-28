import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }
    
    @Override
    public String taskList() {
        return "Deadline " + super.taskList() + "(by: " + by + ")";
    }
    
    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM d yyyy, h a");
        String formattedDtf = this.by.format(dtf);
        return "[D]" + super.toString() + "(by: " + formattedDtf + ")";
    }
}