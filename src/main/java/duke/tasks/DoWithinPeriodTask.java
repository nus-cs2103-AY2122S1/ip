package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DoWithinPeriodTask extends Task {

    protected LocalDate startOfPeriod;
    protected LocalDate endOfPeriod;

    /**
     * Constructs a new DoWithinPeriodTask with description, startOfperiod, endOfPeriod and default isDone is false.
     *
     * @param description Describes the Task
     * @param startOfPeriod The start of the time period that the task is supposed to be done
     * @param endOfPeriod The end of the time period that the task is supposed to be done
     */
    public DoWithinPeriodTask(String description, LocalDate startOfPeriod, LocalDate endOfPeriod) {
        super(description);
        this.startOfPeriod = startOfPeriod;
        this.endOfPeriod = endOfPeriod;
    }

    /**
     * Constructs a new DoWithinPeriodTask with description, startOfPeriod, endOfPeriod and given isDone boolean.
     *
     * @param description Describes the Task
     * @param startOfPeriod The start of the time period that the task is supposed to be done
     * @param endOfPeriod The end of the time period that the task is supposed to be done
     * @param isDone Is the task already done
     */
    public DoWithinPeriodTask(String description, LocalDate startOfPeriod, LocalDate endOfPeriod, boolean isDone) {
        super(description, isDone);
        this.startOfPeriod = startOfPeriod;
        this.endOfPeriod = endOfPeriod;
    }

    /**
     * Returns true if the Task is due on the given date (Last day to complete task).
     *
     * @param dueDate The date to check if this task is on the last day that the task should be completed
     * @return Returns true if this task is due the same date as the given one
     */
    @Override
    public boolean isDue(LocalDate dueDate) {
        return dueDate.isEqual(this.endOfPeriod);
    }



    /**
     * Returns a formatted version with delimiters of this task for saving to file.
     *
     * @return A formatted String representing the data stored in the task
     */
    @Override
    public String getFormattedData() {
        return super.getFormattedData() + "|" + this.startOfPeriod + "|" + this.endOfPeriod;
    }


    /**
     * Returns a letter identifying the Task as a DoWithinPeriodTask.
     *
     * @return A character identifying the Task
     */
    @Override
    public String getTaskIdentifier() {
        return "P";
    }

    /**
     * Returns a string representation of the DoWithinPeriodTask Task.
     *
     * @return String representing the DoWithinPeriodTask Task
     */
    @Override
    public String toString() {
        return "[P][" + getStatusIcon() + "] "
                + this.description + " (between: "
                + this.startOfPeriod.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " and "
                + this.endOfPeriod.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }


    /**
     * Does a deep comparison of this object to another object.
     *
     * @param otherObj The other object to be compared to
     * @return Returns true iff the two objects are of same type and same value in every field
     */
    @Override
    public boolean equals(Object otherObj) {
        if (!(otherObj instanceof DoWithinPeriodTask)) {
            return false;
        } else {
            final DoWithinPeriodTask otherDoWithinPeriodtask = (DoWithinPeriodTask) otherObj;

            if (this.isDone != otherDoWithinPeriodtask.isDone) {
                return false;
            } else if (!this.description.equals(otherDoWithinPeriodtask.description)) {
                return false;
            } else {
                return this.startOfPeriod.equals(otherDoWithinPeriodtask.startOfPeriod) && this.endOfPeriod.equals(otherDoWithinPeriodtask.endOfPeriod);
            }
        }
    }
}
