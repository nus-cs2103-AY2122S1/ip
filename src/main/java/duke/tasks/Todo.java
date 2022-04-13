package duke.tasks;

/**
 * Represents a ToDo task.
 */
public class Todo extends Task {
    private static final String TASK_TAG = "todo";

    public Todo(String taskName) {
        super(taskName);
    }

    public Todo(String taskName, boolean isDone) {
        super(taskName, isDone);
    }

    /**
     * Returns the string format in which this task is to be saved within a file.
     *
     * @return String representation of task for saving within a file.
     */
    public String fileSaveFormat() {
        return String.format("T | %d | %s", this.isDone() ? 1 : 0, this.taskName());
    }

    public static String taskTag() {
        return TASK_TAG;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
