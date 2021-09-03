package duke.task;

import duke.date.Date;

/** Represents an abstract Task that has a Date attribute */
public abstract class DatedTask extends Task {
    /** The Date of the Task. */
    protected Date date;

    /**
     * The DatedTask constructor.
     *
     * @param description The description of the Task.
     * @param date The date of the Task.
     */
    protected DatedTask(String description, Date date) {
        super(description);
        this.date = date;
    }

    /**
     * The DatedTask constructor.
     *
     * @param description The description of the Task.
     * @param date The date of the Task.
     * @param isDone The done status of the Task.
     */
    protected DatedTask(String description, Date date, boolean isDone) {
        super(description, isDone);
        this.date = date;
    }

    /**
     * Date getter.
     *
     * @return The Date attribute of the Task.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Comparator method.
     *
     * @param otherTask The other task to be compared to.
     * @return A number greater than 0 if the date of the Task precedes the other Task's date, 0 if the dates are the
     * same, a number smalled than 0 otherwise.
     */
    @Override
    public int compareTo(Task otherTask) {
        assert otherTask instanceof DatedTask : "CompareTo method only applicable for DatedTasks";
        DatedTask datedTask = (DatedTask) otherTask;
        return date.compareTo(datedTask.date);
    }
}
