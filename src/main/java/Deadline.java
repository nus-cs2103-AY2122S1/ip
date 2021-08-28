import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;
    private String formattedDtf;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM d yyyy, h a");
        String formattedDtf = this.by.format(dtf);
        this.formattedDtf = formattedDtf;
    }

    public String taskListOnDisk() {
        return "Deadline" + super.taskListOnDisk() + this.formattedDtf;
    }
    
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formattedDtf + ")";
    }
}