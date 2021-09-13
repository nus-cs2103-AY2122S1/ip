package titi.task;

/**
 * Represents a Task.
 * Contains String description of the task.
 * Contains boolean value of whether the task has been completed.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initialises a Task instance.
     *
     * @param description description of the task.
     */
    public Task(String description) {
        assert description != "" : "Description of task cannot be empty";
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as completed.
     */
    public void complete() {
        this.isDone = true;
    }

    /**
     * Determines if the task description contain the string in question.
     *
     * @param str string to search for
     * @return boolean value of whether the substring is found
     */
    public boolean isContain(String str) {
        return description.contains(str);
    }

    /**
     * Returns string representation of the task.
     *
     * @return string representation of task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
