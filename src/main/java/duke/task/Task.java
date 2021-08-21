package duke.task;

import duke.date.Date;
import duke.enums.Tasks;
import duke.exception.InvalidDateException;
import duke.exception.UnknownTaskTypeException;
import org.json.simple.JSONObject;

/** Represents a Task object. */
public abstract class Task {
    /** The description of the task */
    protected String description;

    /** The status of the task */
    protected boolean isDone;

    /** Default Task constructor */
    protected Task() {
        this("");
    }
    
    /**
     * Task constructor.
     *
     * @param description the description of the task
     */
    protected Task(String description) {
        this(description, false);
    }

    /**
     * Task constructor.
     *
     * @param description the description of the task
     * @param isDone the status of the task
     */
    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /** Marks the task's status as done */
    public Task markTaskAsDone() {
        isDone = true;
        return this;
    }

    /**
     * Returns the Task as a JSON object.
     *
     * @return A JSON object.
     */
    public abstract JSONObject toJSONObject();

    /**
     * Returns a Task object or its subclass from a JSON object.
     *
     * @param obj The JSON object.
     * @return A Task object.
     * @throws UnknownTaskTypeException If the Task type is not known.
     * @throws InvalidDateException If the given date is badly formatted.
     */
    public static Task fromJSONObject(JSONObject obj) throws UnknownTaskTypeException, InvalidDateException {
        Task task;
        String taskType = (String) obj.get("type");
        String description = (String) obj.get("description");
        boolean isDone = (boolean) obj.get("isDone");
        switch (Tasks.valueOfLabel(taskType)) {
        case DEADLINE:
            task = Deadline.of(description, Date.of((String) obj.get("date")), isDone);
            break;
        case EVENT:
            task = Event.of(description, Date.of((String) obj.get("date")), isDone);
            break;
        case TODO:
            task = Todo.of(description, isDone);
            break;
        default:
            throw new UnknownTaskTypeException(taskType);
        }
        return task;
    }
}
