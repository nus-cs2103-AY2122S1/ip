package duke.task;

import org.json.simple.JSONObject;

public class Event extends Task {

    private String at;

    /**
     * Default constructor for an Event.
     *
     * @param description The description of the Event.
     * @param at The period of time at which the Event is happening.
     */
    protected Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * A constructor for an Event which includes a boolean isCompleted.
     *
     * @param description The description of the Event.
     * @param at The period of time at which the Event is happening.
     * @param isCompleted Whether the Event has been completed.
     */
    protected Event(String description, String at, boolean isCompleted) {
        super(description);
        this.at = at;
        this.isCompleted = isCompleted;
    }

    /**
     * Default factory method for an Event.
     *
     * @param description The description of the Event.
     * @param at The period of time at which the Event is happening.
     * @return A new Event with the given description.
     */
    public static Event of(String description, String at) {
        return new Event(description, at);
    }

    /**
     * A factory method for an Event which includes a boolean isCompleted.
     *
     * @param description The description of the Event.
     * @param at The period of time at which the Event is happening.
     * @param isCompleted Whether the Event has been completed.
     * @return A new Event with the given description.
     */
    public static Event of(String description, String at, boolean isCompleted) {
        return new Event(description, at, isCompleted);
    }

    @Override
    public String toString() {
        return String.format("[E]%s %s %s", isCompleted ? "[X]" : "[ ]", description,
                "(at: " + this.at + ")");
    }

    @Override
    @SuppressWarnings("unchecked")
    public JSONObject toJsonObject() {
        JSONObject obj = new JSONObject();
        obj.put("type", "event");
        obj.put("description", description);
        obj.put("isCompleted", isCompleted);
        obj.put("at", at);
        return obj;
    }

}
