import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDate deadline;

    public Deadline(String taskName, LocalDate deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    @Override
    public String listEntry() {
        return "[D]" + super.listEntry() + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
