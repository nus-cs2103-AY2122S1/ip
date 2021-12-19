package kermit.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Tasks that are associated with a time.
 * e.g a due date or a time when task occurs.
 */
public abstract class DateDependentTask extends Task {
    private LocalDate date;

    /**
     * Constructs DateDependentTask.
     *
     * @param description Description of task.
     * @param date Date related to task.
     */
    public DateDependentTask(String description, LocalDate date) {
        super(description);
        this.date = date;
    }


    /**
     * Constructs DateDependentTask.
     *
     * @param description Description of task.
     * @param date Date related to task.
     * @param isCompleted Boolean to set if task is completed.
     */
    public DateDependentTask(String description, LocalDate date, boolean isCompleted) {
        super(description, isCompleted);
        this.date = date;
    }

    /**
     * Gets string representation of date associated to task.
     *
     * @return Stringified date in form YYYY-MM-DD.
     */
    public String getDateString() {
        return this.date.toString();
    }

    /**
     * Gets string representation of date associated to task.
     *
     * @return Stringified date in form MMM DD YYYY, MMM is the first 3 letters of the month.
     */
    protected String getFormattedDateString() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
