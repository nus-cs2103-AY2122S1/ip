package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a <code>Deadline</code> type of task.
 */
public class Deadline extends Task {
    private final LocalDate deadline;

    /**
     * Constructs an instance of <code>Deadline</code>
     * @param body Body message
     * @param deadline Date to do this task by
     */
    Deadline(String body, LocalDate deadline) {
        super(body, false);
        this.deadline = deadline;
    }

    /**
     * Constructs an instance of <code>Deadline</code>
     * @param body Body message
     * @param done Status of task
     * @param deadline Date to do task by
     */
    Deadline(String body, boolean done, LocalDate deadline) {
        super(body, done);
        this.deadline = deadline;
    }

    /**
     * Returns deadline
     * @return Deadline
     */
    public LocalDate getDeadline() {
        return this.deadline;
    }

    /**
     * Returns <code>Deadline</code> with done set to true
     * @return Done <code>Deadline</code>
     */
    @Override
    Task setDone() {
        return new Deadline(this.getBody(), true, this.deadline);
    }

    @Override
    public String toString() {
        if (this.isDone()) {
            return "[D] [X]"
                    + this.getBody()
                    + " (by: "
                    + this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                    + ")";
        }
        return "[D] [ ]"
                + this.getBody()
                + " (by: "
                + this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + ")";
    }
}
