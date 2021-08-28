package duke;

/**
 *  This class represents a ToDo.
 *  A ToDo: tasks without any date/time attached to it.
 *
 * @author Ryan Tian Jun.
 */
public class ToDo extends Task {

    /**
     * This constructor handles the creation af a new ToDo Task.
     *
     * @param description Task description.
     * @param type Task type: Todo.
     */
    public ToDo(String description, Type type) {
        super(description, type);
    }

    /**
     * This constructor handles the ToDo Task read from the hard drive.
     *
     * @param type Task type: Todo.
     * @param isDone Whether the task has been done or not.
     * @param description Task description.
     */
    public ToDo(Type type, boolean isDone, String description) {
        super(type, isDone, description);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
