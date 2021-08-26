package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with deadline
 */
public class Deadline extends Task{
    protected LocalDateTime by;
    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("E, dd MMM yyyy HHmm");

    /**
     * Class Constructor
     *
     * @param description description of task
     * @param by the deadline of the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, inputFormatter);
    }

    /**
     * Class Constructor
     *
     * @param description description of task
     * @param by the deadline of the task
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Modifies the string representation of a task with deadline
     *
     * @return string representation of a task with deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(outputFormatter) + ")";
    }
}
