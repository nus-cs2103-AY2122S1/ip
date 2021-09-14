package duke.tasks;

/**
 * Class that represents a task. A task has attributes description, isDone and taskType
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;

    /**
     * Public constructor of task class.
     *
     * @param description The text description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = "";
    }

    /**
     * Gets the status icon of the task, if its done, the status icon is X, else it is " ".
     *
     * @return Status icon of task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); //mark done task with X
    }

    /**
     * Marks task as done and changes the status of the task.
     */
    public void markAsDone() {
        this.isDone = true;
        System.out.println(this.toString());

    }

    /**
     * Gets string representation of a task instance.
     *
     * @return String representation of a task instance.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}

