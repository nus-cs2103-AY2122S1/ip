package Duke.Task;

public abstract class Task {
    private final String taskName;
    private boolean status;

    /**
     * Types of Task availble
     */
    public enum Type {
        TODO, DEADLINE, EVENT
    }

    /**
     * Constructor of a Task
     * @param taskName name of the task
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.status = false;
    }

    /**
     * Mark a task status as completed
     */
    public void markAsCompleted() {
        this.status = true;
    }

    /**
     * String representation of a task - X shows that a task is completed
     * @return the String value of a Task
     */
    @Override
    public String toString() {
        if (status) {
            return "[X] " + this.taskName;
        } else {
            return "[ ] " + this.taskName;
        }
    }

    public abstract String save();

    public abstract String getDate();

    public String getTaskName() {
        return this.taskName;
    }
}
