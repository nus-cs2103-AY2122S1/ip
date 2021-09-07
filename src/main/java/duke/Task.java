package duke;

public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Creates a Task object to store details of a task and its status of completion.
     *
     * @param description Task details.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Indicates task's status of completion.
     *
     * @return Status of task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks task as completed.
     */
    public void completeTask() {
        this.isDone = true;
    }

    /**
     * Returns description of task.
     *
     * @return Description of task.
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

}
