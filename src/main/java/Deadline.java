import java.time.LocalDateTime;

public class Deadline extends Task{
    private LocalDateTime deadlineDate;

    public Deadline(String description, LocalDateTime deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    @Override
    public String typeIcon() {
        return "[D]";
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + deadlineDate + ")";
    }
}
