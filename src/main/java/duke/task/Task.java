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
     * Marks a task status as completed
     */
    public void markAsCompleted() {
        this.taskStatus = true;
    }

    /**
     * Returns completed state of a task
     * @return completed state of a task
     */
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

    /**
     * Reverses the Task representation to saved state
     * @return saved state to load to file
     */
    public abstract String save();

    /**
     * Returns the date associated with a task
     * @return date of a task
     */
    public abstract String getDate();

    /**
     * Checks whether a task contains a certain query
     * @param query query entered
     * @return whether task contains query
     */
    public boolean contains(String query) {
        return this.taskName.contains(query);
    }
}
