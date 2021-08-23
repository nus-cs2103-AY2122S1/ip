import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private static final DateTimeFormatter DT_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy h:mma");
    private static final DateTimeFormatter DT_DATA_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy h:mma");

    private LocalDateTime deadline;


    public Deadline(boolean isDone, String description, LocalDateTime deadline) {
        super(isDone, description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", deadline.format(DT_OUTPUT_FORMAT));
    }

    @Override
    public String getData() {
        return String.format("D | %s | %s", super.getData(), deadline.format(DT_DATA_FORMAT));
    }
}
