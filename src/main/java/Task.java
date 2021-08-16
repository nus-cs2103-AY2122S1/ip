public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * A constructor to create a Task object
     *
     * @param description A description about the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * A method to get either "X" for marked task or " " for unmarked task
     *
     * @return Either "X" or " "
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * A method to set isDone to true
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * A method to get the string representation of a task description
     *
     * @return The string representation of a task description
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
