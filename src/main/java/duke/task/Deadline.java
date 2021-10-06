package duke.task;

import java.time.LocalDateTime;

/**
 * Represents a task with deadline
 */
public class Deadline extends TaskWithTime {
    /**
     * Class Constructor
     *
     * @param description description of task
     * @param by the deadline of the task
     */
    public Deadline(String description, LocalDateTime by) {
        super(description, by);
    }

    /**
     * Modifies the string representation of a task with deadline
     *
     * @return string representation of a task with deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + super.dateTime.format(super.outputFormatter) + ")";
    }
}
