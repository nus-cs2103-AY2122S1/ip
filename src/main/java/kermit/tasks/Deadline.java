package kermit.tasks;

import java.time.LocalDate;

/**
 * Task that is associated with a deadline.
 */
public class Deadline extends DateDependentTask {
    /**
     * Deadline constructor.
     *
     * @param description Description of task.
     * @param by Date to finish task by.
     */
    public Deadline(String description, LocalDate by) {
        super(description, by);
    }

    /**
     * Deadline constructor.
     *
     * @param description Description of task.
     * @param by Date to finish task by.
     * @param isCompleted Boolean to set if task is completed.
     */
    public Deadline(String description, LocalDate by, boolean isCompleted) {
        super(description, by, isCompleted);
    }

    /**
     * Get abbreviation of task, usually first letter.
     *
     * @return String abbreviation of task.
     */
    public String getShortForm() {
        return "D";
    }

    /**
     * Get string representation of date associated to task.
     *
     * @return Stringified date in form MMM DD YYYY, MMM is the first 3 letters of the month.
     */
    public String getDateString() {
        return super.getDateString();
    }

    /**
     * Get string representation of deadline task including its short form,
     * completion status and its due date.
     *
     * @return Stringified task.
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + super.getDateString() + ")";
    }
}
