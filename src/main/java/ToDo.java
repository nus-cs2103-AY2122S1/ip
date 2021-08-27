import org.json.simple.JSONObject;

public class ToDo extends Task {

    private boolean isCompleted = false;
    private String description;

    public ToDo(String description) {
        super(description);
        this.description = description;
    }

    @Override
    public String toString() {
        return "[T][ ] " + super.toString();
    }

    @Override
    @SuppressWarnings("unchecked")
    public JSONObject toJsonObject() {
        JSONObject obj = new JSONObject();
        obj.put("type", "todo");
        obj.put("description", description);
        obj.put("isCompleted", isCompleted);
        return obj;
    }
}
