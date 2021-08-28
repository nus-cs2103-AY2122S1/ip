package duke;

/**
 * Represents an element in the <code>TaskList</code>.
 * Split into 3 types: <code>Todo, Deadline, Event</code>.
 */
public class Task {

    protected String description;
    protected boolean isDone;
    protected Category category;

    public enum Category {
        TODO, DEADLINE, EVENT
    }

    /**
     * Returns a Task object.
     *
     * @param description description of Task
     * @param isDone indicates if Task has been completed
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
