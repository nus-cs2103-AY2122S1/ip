package duke;

/**
 * Represents a task
 */
public class Task {
    private final String body;
    private final boolean isDone;

    /**
     * Constructor of <code>Task</code>
     * @param body Body message
     */
    Task(String body) {
        this.body = body;
        this.isDone = false;
    }

    /**
     * Constructor of <code>Task</code>
     * @param body Body message
     * @param done Status of task
     */
    Task(String body, boolean done) {
        this.body = body;
        this.isDone = done;
    }

    /**
     * Returns body message
     * @return Body message
     */
    String getBody() {
        return this.body;
    }

    /**
     * Returns status of task
     * @return Status of task
     */
    boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns <code>Task</code> with status set to done
     * @return Done <code>Task</code>
     */
    Task setDone() {
        return new Task(this.body, true);
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return " [X] " + this.body;
        }
        return " [ ] " + this.body;
    }
}
