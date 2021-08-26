package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;
    private DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("dd MMM uuuu HHmm");
    private DateTimeFormatter saveFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu HHmm");

    public Deadline(String description, LocalDateTime by) {
        super(description, false);
        this.by = by;
    }

    public Deadline(String description, String isDone, LocalDateTime by) {
        super(description, isDone == "1");
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(displayFormatter) + ")";
    }

    @Override
    public String toSaveString() {
        return "D|" + super.toSaveString() + "|" + by.format(saveFormatter);
    }
}
