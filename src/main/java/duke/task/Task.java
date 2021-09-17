package duke.task;

public abstract class Task {
    private final String taskName;
    private boolean taskStatus;

    /**
     * Types of Task available
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
        this.taskStatus = false;
    }


    /**
     * Mark a task status as completed
     */
    public void markAsCompleted() {
        this.taskStatus = true;
    }

    public abstract Task completedTask();

    public boolean isCompleted() {
        return this.taskStatus;
    }

    /**
     * String representation of a task - X shows that a task is completed
     * @return the String value of a Task
     */
    @Override
    public String toString() {
        if (taskStatus) {
            return "[X] " + this.taskName;
        } else {
            return "[ ] " + this.taskName;
        }
    }

    public abstract String save();

    public abstract String getDate();

    public boolean contains(String query) {
        return this.taskName.contains(query);
    }
}
