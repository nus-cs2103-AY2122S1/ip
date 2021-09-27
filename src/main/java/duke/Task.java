package duke;

/**
 * This represents the parent class of all type of tasks Duke handles.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * This constructor creates a Task object which contains the description of task.
     * The task can either be considered done or not according to user command.
     *
     * @param description text description of a general task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * This method marks the task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * This method formats task description by marking tasks that are done with a 'X' and
     * leaving it blank if the task is not considered done.
     *
     * @return String of formatted task description and if task is marked done.
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return (isDone ? "[X] " + this.description : "[ ] " + this.description); // mark done task with X
    }
}
