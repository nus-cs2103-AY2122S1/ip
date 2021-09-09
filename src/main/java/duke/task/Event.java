package duke.task;

public class Event extends Task {

    private String timeFrame;

    /**
     * Constructor for an Event
     * @param description of Event
     * @param timeFrame where Event occurs
     */
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
     * Updates the Event based on the given updated data.
     * @param updatedData is in the form {description}/{timeframe}
     */
    @Override
    public void update(String updatedData) {
        assert updatedData.split("/").length == 2 : "2 fields to update for events";
        String[] updatedFields = updatedData.split("/");
        this.description = updatedFields[0];
        this.timeFrame = updatedFields[1];
    };

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
