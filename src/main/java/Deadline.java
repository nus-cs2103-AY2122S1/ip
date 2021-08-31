import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private LocalDate by;

    public String taskType() {
        return "D";
    }

    public Deadline (String taskDetails, String by) {
        super(taskDetails);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + 
                by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}