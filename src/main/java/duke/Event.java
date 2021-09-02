package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class Event extends Task {
    protected LocalDateTime duration;
    private DateTimeFormatter fmt = new DateTimeFormatterBuilder()
            .appendPattern("dd MMM yyyy")
            .optionalStart()
            .appendPattern(" HH:mm")
            .optionalEnd()
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .toFormatter();

    Event(String desc, LocalDateTime duration) {
        super(desc);
        this.duration = duration;
    }
    @Override
    public String save() {
        return "E | " + (this.isDone ? 1 : 0)
                + " | " + this.description + " | " + this.duration;
    }
    public LocalDateTime getDuration() {
        return this.duration;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + this.getStatusIcon() + "]"
                + " " + this.description + " (at: " + duration.format(fmt) + ")";
    }
}
