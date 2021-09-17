package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;
    private DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("dd MMM uuuu HHmm");
    private DateTimeFormatter saveFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu HHmm");

    /**
     * Constructor
     *
     * @param description Task description
     * @param by Due date
     */
    public Deadline(String description, LocalDateTime by) {
        super(description, false);
        this.by = by;
    }

    /**
     * Constructor for reading file from storage
     *
     * @param description Task description
     * @param isDone Check
     * @param by Due date
     */
    public Deadline(String description, String isDone, LocalDateTime by) {
        super(description, isDone.equals("1"));
        this.by = by;
    }

    /**
     * Snooze the deadline.
     *
     * @param snoozedTime Updated date and time
     * @return Task in string format
     */
    public String snooze(LocalDateTime snoozedTime) {
        this.by = snoozedTime;
        return toString();
    }

    public LocalDateTime getBy() {
        return this.by;
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
