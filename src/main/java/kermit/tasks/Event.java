package kermit.tasks;

import java.time.LocalDate;

/**
 * Task that has a date that it will occur.
 */
public class Event extends DateDependentTask {
    /**
     * Constructs event task.
     *
     * @param description Description of task.
     * @param at Date that task occurs at.
     */
    public Event(String description, LocalDate at) {
        super(description, at);
    }

    /**
     * Constructs event task.
     *
     * @param description Description of task.
     * @param at Date that task occurs at.
     * @param isCompleted Boolean to set if task is completed.
     */
    public Event(String description, LocalDate at, boolean isCompleted) {
        super(description, at, isCompleted);
    }

    /**
     * Gets abbreviation of task, usually first letter.
     *
     * @return String abbreviation of task.
     */
    @Override
    public String getShortForm() {
        return "E";
    }

    /**
     * Gets string representation of event task including its short form,
     * completion status and the date it occurs at.
     *
     * @return Stringified task.
     */
    @Override
    public String toString() {
        return super.toString() + " (at: " + super.getFormattedDateString() + ")";
    }
}
