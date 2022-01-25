package duke;

/**
 * Todo is a class that extends Task.
 *
 * @author meerian
 */
public class Todo extends Task {

    /**
     * Creates a task with the specified description.
     *
     * @param str The event's description.
     * @throws DukeException when the user inputs an empty description.
     */
    public Todo(String str) throws DukeException {
        super(str);
    }

    /**
     * Returns the string representation of the task to be displayed.
     *
     * @return the string representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
