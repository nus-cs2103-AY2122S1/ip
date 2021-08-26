package duke.task;

/**
 * A class that represents a task that is to be done, without a fixed date.
 */
public class Todo extends Task {

    /**
     * Creates a todo task with a task name.
     *
     * @param taskName Short description of the task.
     */
    public Todo(String taskName) {
        super(taskName);
    }

    /**
     * Creates a todo task with a task name and whether or not the task is done.
     *
     * @param taskName Short description of the task.
     * @param isDone   Boolean that represents whether or not the task is done.
     */
    public Todo(String taskName, boolean isDone) {
        super(taskName, isDone);
    }

    @Override
    public String toSaveData() {
        return "T|" + super.toSaveData();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
