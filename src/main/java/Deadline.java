import org.json.simple.JSONObject;

public class Deadline extends Task {

    private String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    public Deadline(String name, String by, boolean isCompleted) {
        super(name);
        this.by = by;
        this.isCompleted = isCompleted;
    }

    @Override
    public String toString() {
        return String.format("[D]%s %s %s", isCompleted ? "[X]" : "[ ]", description,
                "(by: " + by + ")");
    }

    @Override
    @SuppressWarnings("unchecked")
    public JSONObject toJsonObject() {
        JSONObject obj = new JSONObject();
        obj.put("type", "deadline");
        obj.put("description", description);
        obj.put("isCompleted", isCompleted);
        obj.put("by", by);
        return obj;
    }
}
