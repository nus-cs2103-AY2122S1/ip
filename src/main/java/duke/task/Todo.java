package duke.task;

/**
 * Todo is a  task that can be completed without any time constraint.
 */
public class Todo extends Task {
    public Todo(boolean done, String taskName) {
        super(done, taskName);
    }

    /**
     *
     * Turn todo into a string for saving to memory.
     *
     * @return concise string representation of todo.
     */
    @Override
    public String encode() {
        return String.format("T|%s", super.encode());
    }

    /**
     * Turn task into a human readable string form.
     *
     * @return string representation of todo.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
