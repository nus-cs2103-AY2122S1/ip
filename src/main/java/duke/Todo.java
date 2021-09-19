package duke;

/**
 * Represents a <code>Todo</code> type of task
 */
public class Todo extends Task {

    /**
     * Constructs an instance of <code>Todo</code>
     * @param body Body message
     */
    Todo (String body) {
        super(body, false);
    }

    /**
     * Constructs an instance of <code>Todo</code>
     * @param body Body message
     * @param done Status of task
     */
    Todo(String body, boolean done) {
        super(body, done);
    }

    /**
     * Returns <code>Todo</code> with done set to true
     * @return Done <code>Todo</code>
     */
    @Override
    Task setDone() {
        return new Todo(this.getBody(), true);
    }

    @Override
    public String toString() {
        if (this.isDone()) {
            return "[T] [X]" + this.getBody();
        }
        return "[T] [ ]" + this.getBody();
    }
}
