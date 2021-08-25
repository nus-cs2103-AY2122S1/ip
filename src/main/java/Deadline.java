import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDate deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = LocalDate.parse(deadline);
    }

    @Override
    public boolean hasDueDate(LocalDate dueDate) {
        return dueDate.isEqual(this.deadline);
    }

    public Deadline(String description, String deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String getFormattedData() {
        return super.getFormattedData() + "|" + this.deadline;
    }

    @Override
    public String getTaskIdentifier() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + this.description + " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
