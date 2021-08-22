package duke;

/**
 * A wrapper for an duke.task.Event which is a duke.task.Task that start at a specific date/time.
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
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }

    @Override
    public String toData() {
        return "[E] | " + super.toData() + " | " + this.at;
    }
}
