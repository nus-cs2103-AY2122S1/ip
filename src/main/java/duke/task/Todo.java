package duke.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * Checks if the task is marked as done.
     * @return if the task is marked as done.
     */
    public boolean getIsDone() { return this.isDone;}

    /**
     * Retrieves the description of the task instance.
     * @return the string description of the task
     */
    public String getDescription() { return this.description; }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}