package duke.task;

import duke.date.Date;

/** Represents an abstract Task that has a Date attribute */
public abstract class DatedTask extends Task implements Comparable<DatedTask> {
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

    @Override
    public int compareTo(DatedTask otherTask) {
        return date.compareTo(otherTask.date);
    }
}
