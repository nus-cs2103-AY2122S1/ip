package duke;
public class Event extends Task{
    private String eventTime;

    /**
     * Constructor for a Event object (Only takes in content as event time is included in content).
     *
     * @param taskContent Content to be stored in the Event object.
     */
    public Event(String taskContent) {
        super(taskContent.split(" /at ")[0], "E");
        eventTime = Parser.parseTiming(taskContent.split(" /at ")[1]);
        assert this.getType().equals("E") : "Wrong type";
    }

    /**
     * Constructor for a Event object.
     * Takes in content and event time separately.
     *
     * @param taskContent Content to be stored in the Event object.
     * @param eventTime Event time of the Event object.
     */
    public Event(String taskContent, String eventTime) {
        super(taskContent, "E");
        this.eventTime = eventTime;
        assert this.getType().equals("E") : "Wrong type";
    }

    /**
     * Returns the event time of this Event object.
     *
     * @return event time
     */
    @Override
    public String getTiming() {
        assert eventTime != null: "Event time cannot be null";
        return eventTime;
    }

    /**
     * Returns the string representation of this Event object.
     *
     * @return String representation of Event object.
     */
    @Override
    public String toString() {
        if(super.isCompleted()) {
            return "[E][X] " + super.getTaskContent() + " " + "(at: " + eventTime + ")";
        }else {
            return "[E][ ] " + super.getTaskContent() + " " + "(at: " + eventTime + ")";
        }
    }
}
