package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class represents a Deadline Task.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d-M-uuuu H:mm");
    private static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("d MMM uuuu hh:mm a");
    protected LocalDateTime date;

    /**
     * Constructs a Deadline Task that is not done.
     *
     * @param description Description of deadline task.
     * @param by Date that task has to be completed by.
     */
    public Deadline(String description, String by) {
        super(description, false);
        this.date = parseDateTime(by);
    }

    /**
     * Constructs a Deadline Task which can be marked as done.
     *
     * @param description Description of deadline task.
     * @param by Date that task has to be completed by.
     * @param isDone Whether the task is done.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.date = parseDateTime(by);
    }

    private LocalDateTime parseDateTime(String at) {
        // TODO: need to catch exception
        return LocalDateTime.parse(at, FORMATTER);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DISPLAY_FORMATTER) + ")";
    }

    @Override
    public String toFileString() {
        return "D" + super.toFileString() + " | " + date.format(FORMATTER);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deadline) {
            Deadline other = (Deadline) obj;
            return super.equals(obj) && date.equals(other.date);
        }
        return false;
    }
}
