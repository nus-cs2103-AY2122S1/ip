package duke;

/**
 * Represents a task
 */
public class Task {
    private final String body;
    private final boolean done;

    /**
     * Constructor of <code>Task</code>
     * @param body Body message
     */
    Task(String body){
        this.body = body;
        this.done = false;
    }

    /**
     * Constructor of <code>Task</code>
     * @param body Body message
     * @param done Status of task
     */
    Task(String body, boolean done) {
        this.body = body;
        this.done = done;
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
    boolean getDone() {
        return this.done;
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
        if (this.done) {
            return " [X] " + this.body;
        }
        else {
            return " [ ] " + this.body;
        }
    }
}
