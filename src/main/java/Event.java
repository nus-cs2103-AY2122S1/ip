/**
 * Event class used to represent a task that has a start and end date.
 * Contains method that
 * (i) overrides the Parent toString method to display the task type,
 * as well as status and description.
 */
public class Event extends Task {
    protected String timeframe;

    public Event(String description, String timeframe) {
        super(description);
        this.timeframe = timeframe;
    }

    public Event(String done, String description, String timeframe) {
        super(description);
        this.timeframe = timeframe;
        this.isDone = (done.equals("X")) ? true : false;
    }

    /**
     * Overriding toString method to display the relevant information
     *
     * @return String type object that includes the task type, parent
     * toString method(), and timeframe.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.timeframe + ")";
    }
}