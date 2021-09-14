package duke.task;

/**
 * The Todo type task.
 */
public class Todo extends Task {

    /**
     * The constructor for the Todo task
     * @param name Name of the task
     * @param isCompleted Completion status of the task.
     */
    public Todo(String name, boolean isCompleted) {
        super(name, TaskType.T, isCompleted);
    }

    /**
     * Updates the name of the Todo.
     * @param name of the updated task
     * @return a new Todo with the updated name.
     */
    @Override
    public Task updateName(String name) {
        return new Todo(name, this.getCompleted());
    }

    @Override
    public Task updateDateTime(String dateTime) {
        return new Todo(this.getName(), this.getCompleted());
    }

    /**
     * Marks Todo as completed.
     * @return a completed Todo.
     */
    @Override
    public Task complete() {
        return new Todo(this.getName(), true);
    }

    @Override
    public String getDetails() {
        String checkbox = getCheckBox();
        return getTaskTypeString() + checkbox + " " + this.getName();
    }

    @Override
    public String getLabel() {
        return this.getName() + "|";
    }
}
