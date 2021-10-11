package duke.classes.tasks;

/**
 * "to do" task
 */
public class ToDo extends Task {
    /**
     * Class Constructor
     *
     * @param description Description of the Event
     */
    public ToDo(String description) {
        super(description, Task.Type.T);
    }

    /**
     * Class Constructor taking in if the task is done
     *
     * @param description String Description of the Task
     * @param isDone boolean indicating if the task is done
     */
    public ToDo(String description, boolean isDone) {
        super(description, Task.Type.T, isDone);
    }
}
