package duke.task;

import java.time.LocalDate;

/**
 * Ensures that tasks inheriting this class have a date on top of description and done status.
 */
public abstract class TimedTask extends Task {

    protected final LocalDate date;

    /**
     * Constructor for TimedTask.
     * Creates a Task containing a description and a time/time period that is by default undone.
     *
     * @param description Description of the task.
     * @param localDate Date of the task.
     */
    public TimedTask(String description, LocalDate localDate) {
        super(description);
        this.date = localDate;
    }

    /**
     * Returns date stored in this TimedTask.
     *
     * @return LocalDate Date stored in TimedTask.
     */
    public LocalDate getTime() {
        return this.date;
    }

    /**
     * Indicates whether another object is equals to this TimedTask.
     *
     * @param obj Other object to be compared to.
     * @return A boolean indicating whether the other object is equals to this TimedTask.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TimedTask) {
            TimedTask other = (TimedTask) obj;

            // Check if done status, description and time are the same.
            boolean isDoneStatusSame = this.isDone == other.isDone;
            boolean isDescriptionSame = this.description.equals(other.description);
            boolean isTimeSame = this.date.equals(other.date);

            return (isDoneStatusSame && isDescriptionSame && isTimeSame);
        }
        return false;
    }
}
