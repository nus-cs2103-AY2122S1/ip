package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class Deadline extends Task {
    protected LocalDateTime deadline;
    private DateTimeFormatter fmt = new DateTimeFormatterBuilder()
            .appendPattern("dd MMM yyyy")
            .optionalStart()
            .appendPattern(" HH:mm")
            .optionalEnd()
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .toFormatter();

    Deadline(String desc, LocalDateTime deadline) {
        super(desc);
        this.deadline = deadline;
    }

    @Override
    public String save() {
        return "D | " + (this.isDone ? 1 : 0)
                + " | " + this.description + " | " + this.deadline;
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "]" + " "
                + this.description + " (by: " + deadline.format(fmt) + ")";
    }
}
