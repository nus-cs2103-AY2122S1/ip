package duke.task;

import duke.exception.InvalidInputException;
import org.json.simple.JSONObject;

public abstract class Task {

    protected boolean isCompleted;
    protected String description;

    public Task(String description) {
        this.isCompleted = false;
        this.description = description;
    }

//    public boolean checkCompletion() {
//        return this.isCompleted;
//    }

//    public void complete() {
//        this.isCompleted = true;
//    }

    public abstract String toString();

    public abstract JSONObject toJsonObject();

    public static Task fromJsonObject(JSONObject obj) throws InvalidInputException {
        Task task;
        String taskType = (String) obj.get("type");
        String description = (String) obj.get("description");
        boolean isCompleted = (boolean) obj.get("isCompleted");
        String by = (String) obj.get("by");
        String at = (String) obj.get("at");
        switch (taskType) {
            case "deadline":
                task = Deadline.of(description, by, isCompleted);
                break;
            case "event":
                task = Event.of(description, at, isCompleted);
                break;
            case "todo":
                task = ToDo.of(description, isCompleted);
                break;
            default:
                throw new InvalidInputException("Invalid input!");
        }
        return task;
    }

}
