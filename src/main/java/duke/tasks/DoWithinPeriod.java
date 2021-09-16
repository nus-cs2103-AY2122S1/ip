package duke.tasks;

/**
 * Reflects a particular task to be completed within a certain period
 */
public class DoWithinPeriod extends Task {

    /** Indicates the start date that the task can be started on */
    protected String startDate;

    /** Indicates the end date that the task should be finished by */
    protected String endDate;

    /**
     * Constructor to create an DoWithinPeriod task containing a description, and the start/end date of the task
     */
    public DoWithinPeriod(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Internal constructor called to indicate that the task is completed.
     */
    private DoWithinPeriod(String description, String startDate, String endDate, Boolean isDone) {
        super(description, isDone);
        this.startDate = startDate;
        this.endDate = endDate;
    }


    /**
     * Method to get the start date of the task
     *
     * @return start date in YYYY-MM-DD format
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Method to get the end date of the task
     *
     * @return end date in YYYY-MM-DD format
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Method to obtain a completed task
     *
     * @return a completed DoWithinPeriod task with the exact same description & start/end dates
     */
    public DoWithinPeriod markAsDone() {
        return new DoWithinPeriod(this.description, this.startDate, this.endDate, true);
    }

    @Override
    public String toString() {
        return "[DW]" + super.toString() + " (between: " + startDate + " & " + endDate + ")";
    }
}
