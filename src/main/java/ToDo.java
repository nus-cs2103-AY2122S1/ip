/**
 * Encapsulates the name of the todo task.
 */

public class ToDo extends Task {
    /**
     * Constructs the Todo task with a name.
     * @param name Name of the Todo task.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Converts the ToDo task into a string
     * @return String with the type of the task (todo) and the name of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
