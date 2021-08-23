import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private static final DateTimeFormatter DT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy h:mma");

    private LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", deadline.format(DT_FORMAT));
    }
}
