/**
 * A class that abstracts a task.
 */
public abstract class Task {

    public abstract String taskDescription();

    // The type of task
    public enum Type {
        TODO,
        DEADLINE,
        EVENT
    }

    private boolean isDone = false;
    private final String taskName;
    private final Type type;

    public Task(String taskName, Type type) {
        this.taskName = taskName;
        this.type = type;
    }

    /**
     * Marks a task as completed.
     */
    public void setAsFinished() {
        this.isDone = true;
    }

    /**
     * A string representation of the task with its task name and its completion status.
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return typeString() +  "[X] " + this.taskDescription();
        }
        return typeString() + "[] " + this.taskDescription();
    }

    public String getTaskName() {
        return taskName;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * Prints the string representation of the type of task.
     * @return The required string representation.
     */
    public String typeString() {
        if (this.type.equals(Type.TODO)) {
            return "[T]";
        } else if (this.type.equals(Type.EVENT)) {
            return "[E]";
        } else {
            return "[D]";
        }
    }
}
