/**
 * This class simulates a special Task with a designated time
 * and date (if applicable).
 */
public class Event extends Task {

    /**
     * Constructor of an Event.
     * @param description description of the event.
     */
    public Event(String description) {
        super(description);
    }

    /**
     * Returns the description of the current Event
     * @return the description of the current Event
     */
    @Override
    public String getDescription() {
        String temp = super.getDescription();
        temp = temp.replace("event ", "");
        if (temp.contains("/at")) {
            temp = temp.replace("/at", "(at:");
            return temp + ")";
        }
        return "(" + temp + ")";
    }

    /**
     * Marks the current Event as done.
     */
    @Override
    public void markedDone() {
        super.markedDone();
    }

    /**
     * Returns the string representation of the
     * completion status of the Event.
     * @return the string representation of the
     *         completion status of the Event.
     */
    @Override
    public String getStatusIcon() {
        return "[E]" + super.getStatusIcon();
    }

    /**
     * Returns the string representation for the Event's description
     * and completion status.
     * @return the string representation for the Event's description
     *         and completion status.
     */
    @Override
    public String printTask() {
        return this.getStatusIcon() + " " + this.getDescription();
    }
}
