package duke.task;

/**
 * Represents a task.
 */
public abstract class Task {
    public static final String TASK_COMPLETED_SYMBOL = "X";
    public static final String FIELD_SEPARATOR = " | ";

    private String name;
    private boolean isDone;

    protected Task(String name) {
        this(name, false);
    }

    protected Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    protected Task markAsDone() {
        isDone = true;
        return this;
    }

    /**
     * Returns the name of the task.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns whether the task is marked as completed.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the string representation of the completion status of the <code>Task</code>.
     */
    public String getStatusIcon() {
        return isDone ? TASK_COMPLETED_SYMBOL : " ";
    }

    /**
     * Returns the text representation of the <code>Task</code>.
     */
    public abstract String toText();

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), name);
    }
}
