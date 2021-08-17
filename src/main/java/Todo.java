/**
 * This class keeps track of todo type tasks.
 */
public class Todo extends Task{

    /**
     * Constructor, to initialize a todo task.
     *
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the todo task description and its status in an
     * organised format.
     *
     * @return A String including the todo task details.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
