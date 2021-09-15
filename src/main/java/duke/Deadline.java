package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline Task.
 */
public class Deadline extends Task {
    private static final String DATE_FORMAT = "dd MMM yyyy";
    private static final String TASK_TYPE = "D";
    private LocalDate date;

    /**
     * Class constructor specifying the task and the deadline.
     * @param task Task to be done.
     * @param date Deadline by which the task needs to be done.
     */
    public Deadline(String task, LocalDate date) {
        super(task, TASK_TYPE);
        this.date = date;
    }

    /**
     * Class constructor specifying the task, whether the task was completed and the deadline.
     * @param task Task to be done.
     * @param completed Whether the task has been completed.
     * @param date Deadline by which the task needs to be done.
     */
    public Deadline(String task, boolean completed, LocalDate date) {
        super(task, completed, TASK_TYPE);
        this.date = date;
    }

    /**
     * Returns the dateline of the task in a pre-defined format.
     * @return Deadline of task.
     */
    public String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)", this.getTaskType(), this.getCompletedMarker(),
                this.getTask(), this.getDate());
    }

    @Override
    public String parseForStorage() {
        return String.format("%s | %d | %s | %s", this.getTaskType(), this.getIsCompleted() ? 1 : 0,
                this.getTask(), this.getDate());
    }
}
