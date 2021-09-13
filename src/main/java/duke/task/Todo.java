package duke.task;

public class Todo extends Task {
    protected String priority;

    public Todo(String description, String priority) {
        super(description);
        this.priority = priority;
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return if the task is marked as done.
     */
    public boolean getIsDone() { return this.isDone;}

    /**
     * Retrieves the description of the task instance.
     *
     * @return the string description of the task
     */
    public String getDescription() { return this.description; }

    public String getPriority() {return this.priority;}

    @Override
    public String toString() {
        return "[T]" + super.toString() + " [Priority " + this.priority + "]";
    }
}