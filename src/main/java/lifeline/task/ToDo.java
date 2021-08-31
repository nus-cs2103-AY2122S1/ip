package lifeline.task;

/**
 * The class ToDo represents a task with no deadline.
 * It is a subclass of Task.
 */
public class ToDo extends Task {

    /**
     * Default constructor for a ToDo.
     *
     * @param name Name of ToDo.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Constructor to be used when loading saved ToDo.
     *
     * @param name Name of ToDo.
     * @param isDone Indicate whether the ToDo is completed.
     */
    public ToDo(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
