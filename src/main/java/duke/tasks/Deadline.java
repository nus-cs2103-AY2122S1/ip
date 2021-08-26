package duke.tasks;

import java.time.LocalDateTime;

/**
 * Class to handle the Deadline task.
 */
public class Deadline extends Task {

    private Deadline(String description, LocalDateTime deadline) {
        super(description, "D", deadline);
    }

    /**
     * Factory method to create a new deadline
     *
     * @param description The description of the task.
     * @param time The time of the deadline.
     * @return A new instance of Deadline.
     */
    public static Deadline create(String description, LocalDateTime time) {
        return new Deadline(description, time);
    }

    @Override
    public String toString() {
        return '[' + this.taskType + ']' + '[' + getStatusIcon()
                + ']' + ' ' + this.description
                + String.format(" (by: %s)", this.time.format(formatter));
    }
}
