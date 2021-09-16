package poseidon.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {

    private LocalDateTime byDateTime;

    /**
     * Constructs a new Deadline using the given description, done status and by time.
     *
     * @param description Description of the new Deadline.
     * @param byDateTime By time of the new Deadline.
     */
    public Deadline(String description, LocalDateTime byDateTime) {
        super(description);
        this.byDateTime = byDateTime;
    }

    /**
     * Constructs a new Deadline using the given description, done status and by time.
     *
     * @param description Description of the new Deadline.
     * @param isDone Done status of the new Deadline.
     * @param byDateTime By time of the new Deadline.
     */
    public Deadline(String description, boolean isDone, LocalDateTime byDateTime) {
        super(description);
        this.isDone = isDone;
        this.byDateTime = byDateTime;
    }

    @Override
    public String toString() {
        return "[D]" + (isDone ? "[X] " : "[ ] ") + description + " (by: "
                + byDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT))
                + ")";
    }

    @Override
    public String toStorage() {
        return ("D%" + isDone + "%" + description + "%" + byDateTime + "\n");
    }

    @Override
    protected LocalDateTime getDateTime() {
        return byDateTime;
    }
}
