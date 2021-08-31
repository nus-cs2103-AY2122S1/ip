package duke.task;

/**
 * A type of task which only contains the name of the task.
 */
public class Todo extends Task {
    /**
     * Constructor of Todo task.
     *
     * @param task The name of task.
     */
    public Todo(String task) {
        super(task);
    }

    /**
     * Constructor of Todo task, indicating whether the task is done or not.
     *
     * @param task The name of task.
     * @param isDone Whether the task is done or not.
     */
    public Todo(String task, boolean isDone) {
        super(task, isDone);
    }

    /**
     * Returns the String representation of the todo task.
     *
     * @return The String representation of the todo task.
     */
    @Override
    public String toString() {
        String finished = " ";
        if (this.isDone()) {
            finished = "X";
        }
        return "[T]" + "[" + finished + "] " + this.getTaskName();
    }

    /**
     * Returns the representation of the todo task when it is stored.
     *
     * @return the representation of the todo task when it is stored.
     */
    @Override
    public String toStoredString() {
        int finished = isDone() ? 1 : 0;
        return "T | " + finished + " | " + this.getTaskName();
    }
}
