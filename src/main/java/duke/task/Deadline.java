package duke.task;

import duke.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String by;
    protected LocalDateTime localDateTime;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the deadline of the deadline task.
     *
     * @return The deadline.
     */
    public String getBy() {
        assert by != null : "by variable should not be null";
        return by;
    }

    /**
     * Returns the formatted string representation of the task.
     *
     * @return String representation of task.
     */
    @Override
    public String toString() {
        assert by != null : "by variable should not be null";
        localDateTime = new Parser().parseLocalDateTime(by);
        return "[D]" + super.toString() + " (by: " + localDateTime.format(dateTimeFormatter) + ")";
    }

}

