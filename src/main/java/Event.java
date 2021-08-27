import org.json.simple.JSONObject;

public class Event extends Task {

    private boolean isCompleted = false;
    private String description;
    private String at;

    public Event(String name, String at) {
        super(name);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E][ ] " + super.toString() + "(at: " + this.at + ")";
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
