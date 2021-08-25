package duke.task;

public class Event extends Task {

    private String timeFrame;

    public Event (String description, String timeFrame) {
        super(description);
        this.timeFrame = timeFrame;

    }

    /**
     * Returns a comma separated string representation of the Event's data
     * for data storage.
     *
     * @return String that represents the Event's data
     */
    @Override
    public String getData() {
        return String.format("E,%s,%s", this.timeFrame, super.getData());
    }

    /**
     * Returns the string representation of an Event.
     *
     * @return String representing an Event
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.timeFrame);
    }
}
