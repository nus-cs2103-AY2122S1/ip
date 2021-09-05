package duke.task;

/**
 * A wrapper for a duke.task.Event which is a duke.task.Task that start at a specific date/time.
 *
 * @author Wong Yun Rui Chris
 */
public class Event extends Task {
    protected String at;

    /**
     * A public constructor to initialise an duke.task.Event duke.task.Task.
     *
     * @param description The String description/name of the task
     * @param at The String describing when the duke.task.Event takes place
     * @param isDone The Boolean of if the task is done
     */
    public Event(String description, String at, Boolean isDone) {
        super(TaskName.EVENT, description, isDone);
        this.at = at;
    }

    /**
     * Returns the string representation of this Event task that is to be displayed
     * by Duke. It comprises the tag for Event, description of this Event and the
     * Event of this task.
     *
     * @return The string representation of this Event task
     */
    @Override
    public String toString() {
        return taskName.getTaskIcon() + super.toString() + " (at: " + this.at + ")";
    }

    /**
     * Returns the data representation for this Event task that is to be saved
     * and used on initial execution of Duke.
     *
     * @return The String representation of the data of this Event task
     */
    @Override
    public String toData() {
        return taskName.getTaskIcon() + " | " + super.toData() + " | " + this.at;
    }
}
