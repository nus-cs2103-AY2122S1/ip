package duke;

/**
 * Represents a task with a description and its status.
 * Is extended by several other classes. (Todo, Deadline, Event).
 */
public class Task {
    private String desc;
    private boolean isDone;

    /**
     * Creates a Task object.
     *
     * @param desc Description of the Task.
     */
    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    /**
     * Creates a Task object.
     *
     * @param desc Description of the Task.
     * @param isDone Status of the Task.
     */
    public Task(String desc, boolean isDone) {
        this.desc = desc;
        this.isDone = isDone;
    }

    public String getDesc() {
        return desc;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a formatted String based on the status of the task.
     *
     * @return the formatted String.
     */
    public String statusIcon() {
        return isDone ? "[X] " : "[ ] ";
    }

    /**
     * Returns the task as a String formatted to be saved.
     *
     * @return The formatted String.
     */
    public String saveTask() {
        return "T|" + isDone + "\\|" + desc;
    }
}
