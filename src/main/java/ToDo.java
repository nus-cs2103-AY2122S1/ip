import org.json.simple.JSONObject;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
        this.description = description;
    }

    public ToDo(String description, boolean isCompleted) {
        super(description);
        this.isCompleted = isCompleted;
    }

    @Override
    public String toString() {
        return String.format("[T]%s %s ", isCompleted ? "[X]" : "[ ]", description);
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
