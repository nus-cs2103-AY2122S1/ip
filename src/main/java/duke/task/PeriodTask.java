package duke.task;

import java.time.LocalDate;

/**
 * Represents a task that must be done within a period of time.
 */
public class PeriodTask extends Task {
    protected LocalDate startDate;
    protected LocalDate endDate;

    /**
     * Constructor for PeriodTask.
     * Creates a PeriodTask containing a description and a start and end date.
     *
     * @param description Description of the task.
     */
    public PeriodTask(String description, LocalDate startDate, LocalDate endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns the time period for this PeriodTask as an array.
     *
     * @return LocalDate array of two elements, the start and end date.
     */
    public LocalDate[] getPeriod() {
        return new LocalDate[]{startDate, endDate};
    }

    @Override
    public String getTaskType() {
        return "P";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PeriodTask) {
            PeriodTask other = (PeriodTask) obj;

            // Check if done status, description and start & end date are the same.
            boolean isDoneStatusSame = this.isDone == other.isDone;
            boolean isDescriptionSame = this.description.equals(other.description);
            boolean isStartDateSame = this.startDate.equals(other.startDate);
            boolean isEndDateSame = this.endDate.equals(other.endDate);

            return (isDoneStatusSame && isDescriptionSame && isStartDateSame && isEndDateSame);
        }
        return false;
    }
}
