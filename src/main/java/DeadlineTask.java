import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    LocalDateTime deadline;

    public DeadlineTask(String content, boolean isDone, LocalDateTime deadline) {
        super(content, isDone);
        this.deadline = deadline;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return String.format(
                "[D]%s (by: %s)",
                super.toString(),
                this.deadline.format(DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm")));
    }
}
