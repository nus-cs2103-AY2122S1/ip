package duke.task;

import duke.exception.InvalidInputException;
import duke.util.Ui;
import org.json.simple.JSONObject;

public class ToDo extends Task {

    protected ToDo(String description) {
        super(description);
        this.description = description;
    }

    protected ToDo(String description, boolean isCompleted) {
        super(description);
        this.isCompleted = isCompleted;
    }

    public static ToDo of(String description) {
        return new ToDo(description);
    }

    public static ToDo of(String description, boolean isCompleted) {
        return new ToDo(description, isCompleted);
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
