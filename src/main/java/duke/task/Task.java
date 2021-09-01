package duke.task;

import duke.InvalidInputException;
import org.json.simple.JSONObject;

public abstract class Task {

    protected boolean isCompleted;
    protected String description;

    public Task(String description) {
        this.isCompleted = false;
        this.description = description;
    }

    public boolean checkCompletion() {
        return this.isCompleted;
    }

    public void complete() {
        this.isCompleted = true;
    }

    public String toString() {
        return description;
    }

    public JSONObject toJsonObject() {
        // OOP!
        return null;
    }

    public static Task fromJsonObject(JSONObject obj) throws InvalidInputException {
        Task task;
        String taskType = (String) obj.get("type");
        String description = (String) obj.get("description");
        boolean isCompleted = (boolean) obj.get("isCompleted");
        String by = (String) obj.get("by");
        String at = (String) obj.get("at");
        switch (taskType) {
            case "deadline":
                task = new Deadline(description, by, isCompleted);
                break;
            case "event":
                task = new Event(description, at, isCompleted);
                break;
            case "todo":
                task = new ToDo(description, isCompleted);
                break;
            default:
                throw new InvalidInputException("Invalid input!");
        }
        return task;
    }

}
