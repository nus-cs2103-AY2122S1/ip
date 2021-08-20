package duke.task;

import duke.date.Date;
import duke.enums.Tasks;
import duke.exception.InvalidDateException;
import duke.exception.UnknownTaskTypeException;
import org.json.simple.JSONObject;

/**
 * Represents a task object.
 */
public abstract class Task {
    /** The description of the task */
    protected String description;

    /** The status of the task */
    protected boolean isDone;

    /** duke.tasks.Task constructor */
    protected Task() {
        this("");
    }
    
    /**
     * duke.tasks.Task constructor.
     *
     * @param description the description of the task
     */
    protected Task(String description) {
        this(description, false);
    }

    /**
     * duke.tasks.Task constructor.
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

    public abstract JSONObject toJSONObject();

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
