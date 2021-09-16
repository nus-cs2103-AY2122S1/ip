package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates a deadline to meet, which can be entered into the to-do-list.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructor for a deadline task.
     *
     * @param description A short description of the task.
     * @param by The deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        LocalDate d = LocalDate.parse(by);
        this.by = d;
    }

    /**
     * Constructor for a deadline task.
     *
     * @param description A short description of the task.
     * @param isDone A boolean to indicate whether the task is already done.
     * @param by The deadline of the task.
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        LocalDate d = LocalDate.parse(by);
        this.by = d;
    }

    /**
     * Constructor for a deadline task.
     *
     * @param description A short description of the task.
     * @param isDone A boolean to indicate whether the task is already done.
     * @param tag The tag to attach to the task.
     * @param by The deadline of the task.
     */
    public Deadline(String description, boolean isDone, String tag, String by) {
        super(description, isDone, tag);
        LocalDate d = LocalDate.parse(by);
        this.by = d;
    }

    /**
     * Returns the string representation of the task, in a format suitable for storing in a text file.
     *
     * @return The string representation of the task, in a format suitable for storing in a text file.
     */
    @Override
    public String toStringForFile() {
        return "D | " + super.toStringForFile() + " | " + this.by;
    }

    @Override
    public String toString() {
        String icon = "[D]";
        String deadline = this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return icon + super.toString() + " (by: " + deadline + ")";
    }
}
