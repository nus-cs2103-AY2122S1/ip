import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    protected LocalDateTime deadline;

    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    public DeadlineTask(String isCompleted, String description, String deadline) {
        super(isCompleted, description);
        this.by = deadline;
    }

    public String getDeadline() {
        return this.by;
    }

    public String getType() {
        return "DEADLINE";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm"))
                + ")";
    }
}