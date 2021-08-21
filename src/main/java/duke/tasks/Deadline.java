package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String marker = "[D]";
    private LocalDateTime deadline;

    public Deadline(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return this.marker + super.toString()
                + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy EEE HH:mm")) + ")";
    }
}
