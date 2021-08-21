package duke.task;

import org.json.simple.JSONObject;

/**
 * Represents a duke.tasks.Todo object.
 */
public class Todo extends Task {
    /**
     * duke.tasks.Todo constructor.
     *
     * @param description the description
     */
    Todo(String description) {
        super(description);
    }

    Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Factory duke.tasks.Todo method.
     *
     * @param description the description
     * @return a new duke.tasks.Todo object
     */
    public static Todo of(String description) {
        return new Todo(description);
    }

    public static Todo of(String description, boolean isDone) {
        return new Todo(description, isDone);
    }

    @Override
    public String toString() {
        return String.format("[T]%s %s", isDone ? "[X]" : "[ ]", description);
    }

    @Override
    @SuppressWarnings("unchecked")
    public JSONObject toJSONObject() {
        JSONObject obj = new JSONObject();
        obj.put("type", "todo");
        obj.put("description", description);
        obj.put("isDone", isDone);
        return obj;
    }
}
