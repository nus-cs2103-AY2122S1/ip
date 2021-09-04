package duke.task;

/**
 * Todo class for implementing tasks that are to be done.
 *
 * @author Benjamin Lui
 */
public class Todo extends Task {
    /**
     * Constructor for the Todo class.
     * @param description name of the task to be done
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor for the Todo class.
     * @param description name of the task to be done
     * @param isDone whether the task is done based on its status icon
     */
    public Todo(String description, String isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
