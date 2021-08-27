package fan.cs2103t.duke.task;

/**
 * Represents a task.
 * <p>
 * <code>Task</code> is an abstract class and it has three subclasses <code>Task</code>:
 * <code>Todo</code>, <code>Deadline</code>, <code>Event</code>.
 */
public abstract class Task {

    private final String description;
    private boolean isDone;
    private final TaskType taskType;

    /**
     * Constructs a task with the specified description and type,
     * with an initial completion status "not done".
     *
     * @param description the description of the task.
     * @param taskType the type of the task.
     */
    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    /**
     * Returns the completion status of this task.
     *
     * @return the completion status of this task.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the description of this task.
     *
     * @return the description of this task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the description of this task with its type and completion status.
     *
     * @return the description of this task with its type and completion status.
     */
    public String getDescriptionWithStatus() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public TaskType getTaskType() {
        return this.taskType;
    }

    /**
     * Marks this task as done.
     * This operation permanently changes the completion status of this task to be "done".
     */
    public void markAsDone() {
        this.isDone = true;
    }

}
