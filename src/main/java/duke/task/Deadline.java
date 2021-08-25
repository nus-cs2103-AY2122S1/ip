package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy hh:mma");
    private static final DateTimeFormatter saveFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    @Override
    public String formatSave() {
        return "D%," + isDone + "%," + description + "%," + by.format(saveFormatter);
    }
}
