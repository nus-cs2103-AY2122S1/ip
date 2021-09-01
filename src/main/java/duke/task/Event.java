package duke.task;

import org.json.simple.JSONObject;

public class Event extends Task {

    private String at;

    protected Event(String description, String at) {
        super(description);
        this.at = at;
    }

    protected Event(String description, String at, boolean isCompleted) {
        super(description);
        this.at = at;
        this.isCompleted = isCompleted;
    }

    public static Event of(String description, String at) {
        return new Event(description, at);
    }

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
