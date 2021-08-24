package duke;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String deadline = "";

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
     * A method to get either "X" for marked tasks or " " for unmarked tasks
     *
     * @return Either "X" or " "
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * A method that returns "Done" for marked tasks and "NotDone" for unmarked tasks
     *
     * @return
     */
    public String getStatus() {
        return isDone ? "Done" : "NotDone";
    }

    /**
     * A method to return the description of a task
     *
     * @return The description of a task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * A method to return the deadline of a task
     *
     * @return The deadline of a task
     */
    public String getDeadline() {
        return this.deadline;
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
