package TiTi.task;

/**
 * Represents a Task.
 * Contain String description of the task.
 * Contain boolean value of whether the task has been completed.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task class.
     *
     * @param description description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Mark the task as completed.
     */
    public void complete() {
        this.isDone = true;
    }

    /**
     * Determine if the task description contain the string in question.
     *
     * @param str string to search for
     * @return boolean value of whether the substring is found
     */
    public boolean checkString(String str) {
        return description.contains(str);
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}