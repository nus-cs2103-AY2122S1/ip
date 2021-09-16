package energy.task;

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

    /**
     * Marks the current todo task as done. Returns a new instance of the task to maintain immutability.
     *
     * @return A task with the same task name but is marked as done.
     */
    @Override
    public Task markAsDone() {
        return new Todo(taskName, true);
    }

    /**
     * Converts the todo task data into its corresponding save file data format.
     *
     * @return A string to represent the data of the todo task in the save file.
     */
    @Override
    public String toSaveData() {
        return "T|" + super.toSaveData();
    }

    /**
     * Returns a string representation of the current todo task.
     *
     * @return A string that contains the information of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
