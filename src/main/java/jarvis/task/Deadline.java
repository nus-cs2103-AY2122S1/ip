package jarvis.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static final String OUTPUT_FORMAT = "MMM d yyyy HHmm";
    private LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(OUTPUT_FORMAT);
        String formattedDeadline = this.deadline.format(dateTimeFormatter);
        return String.format("[D]%s (by: %s)", super.toString(), formattedDeadline);
    }
}
