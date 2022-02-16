package duke.task;

/**
 * Todo class which is a subclass of Task.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo task.
     *
     * @param description the description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the todo task.
     *
     * @return string representation of todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation which is use to store the tasks.
     *
     * @return string representation used for storing task.
     */
    @Override
    public String toDataFormat() {
        return String.format("T | %s | %s | %s", isDone ? "1" : "0", description, getPlacesRepresentation());
    }
}
