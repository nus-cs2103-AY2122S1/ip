package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public String formatForSave() {
        return "D" + super.formatForSave() + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + by.format(DateTimeFormatter.ofPattern("dd MMM yyyy h.mma")) + ")";
    }
}