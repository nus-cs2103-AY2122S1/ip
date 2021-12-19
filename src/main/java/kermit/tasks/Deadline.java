package kermit.tasks;

import java.time.LocalDate;

/**
 * Task that is associated with a deadline.
 */
public class Deadline extends DateDependentTask {
    /**
     * Constructs Deadline task.
     *
     * @param description Description of task.
     * @param by Date to finish task by.
     */
    public Deadline(String description, LocalDate by) {
        super(description, by);
    }

    /**
     * Constructs Deadline task.
     *
     * @param description Description of task.
     * @param by Date to finish task by.
     * @param isCompleted Boolean to set if task is completed.
     */
    public Deadline(String description, LocalDate by, boolean isCompleted) {
        super(description, by, isCompleted);
    }

    /**
     * Gets abbreviation of task, usually first letter.
     *
     * @return String abbreviation of task.
     */
    @Override
    public String getShortForm() {
        return "D";
    }

    /**
     * Gets string representation of deadline task including its short form,
     * completion status and its due date.
     *
     * @return Stringified task.
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + super.getFormattedDateString() + ")";
    }
}
