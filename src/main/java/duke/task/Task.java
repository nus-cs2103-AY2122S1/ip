package duke.task;

import duke.exception.InvalidInputException;
import org.json.simple.JSONObject;

public abstract class Task {

    protected boolean isCompleted;
    protected String description;

    /**
     * Default constructor for a Task.
     *
     * @param description The description of a task.
     */
    public Task(String description) {
        this.isCompleted = false;
        this.description = description;
    }

    /**
     * Checks if a specific task has been completed.
     *
     * @return A boolean representing whether a task has been completed.
     */
    public boolean checkCompletion() {
        return this.isCompleted;
    }

    /**
     * Marks a task as completed.
     */
    public void complete() {
        this.isCompleted = true;
    }

    public String getDescription() {
        return description;
    }

    public abstract String toString();

    //@@author huizhuansam-reused
    //General method as well as overridden methods were adapted from his repo
    public abstract JSONObject toJsonObject();
    //@@author

    /**
     * Converts a JSON Object into a Task.
     *
     * @param obj The Task in JSON Object format.
     * @return The task as a Task.
     * @throws InvalidInputException If the input is deemed invalid.
     */
    //@@author huizhuansam-reused
    //Used for his ip
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
    //@@author

}
