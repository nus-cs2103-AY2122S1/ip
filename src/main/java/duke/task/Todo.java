package duke.task;

/**
 * Represents a Todo, a subtype of Task.
 *
 * @author Hanif Kamal
 */
public class Todo extends Task{

    /**
     * Class constructor to initialize a Todo.
     * @param taskName The name or description of the Todo.
     * @param doneStatus Whether or not the Todo is done.
     */
    public Todo(String taskName, boolean doneStatus) {
        super(taskName, doneStatus);
    }

    /**
     * Returns the string representation of the Todo.
     * @return The string representation of the Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}