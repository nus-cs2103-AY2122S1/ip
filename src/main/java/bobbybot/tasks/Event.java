package bobbybot.tasks;

/** Represents a bobbybot.tasks.Task that should be completed at a specified time period
 */
public class Event extends Task {
    protected String at;

    /**
     *
     * @param description description of task
     * @param at time period of bobbybot.tasks.Event (start-end)
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Overloaded constructor to specify if completed
     * @param description description of task
     * @param at time period of Event (start-end)
     * @param isDone boolean flag if task is done
     */
    public Event(String description, String at, boolean isDone) {
        super(description);
        this.at = at;
        this.isDone = isDone;
    }

    /**
     * Getter for string in save-friendly format
     */
    @Override
    public String getSaveFormatString() {
        int isDoneInt = isDone ? 1 : 0;
        return ("E," + isDoneInt + "," + description + "," + at);
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
