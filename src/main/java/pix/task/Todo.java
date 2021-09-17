package pix.task;

/**
 * Todo task to be completed.
 */
public class Todo extends Task {
    /**
     * Constructor for the todo task.
     *
     * @param name Name of the task.
     */
    public Todo(String name, boolean done) {
        super(name, done);
    }

    /**
     * Constructor for the todo task.
     *
     * @param name Name of the task.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Formats the task into the format to save it in a text file.
     *
     * @return Returns the text representation of the task.
     */
    @Override
    public String getSaveName() {
        return isDone ? "T|1|" + name : "T|0|" + name;
    }

    /**
     * String representation of a todo task.
     *
     * @return Returns the string representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
