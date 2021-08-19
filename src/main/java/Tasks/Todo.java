package Tasks;

/**
 * Todo task which contains only the task description
 */
public class Todo extends Task {

    /**
     * A public constructor to create a ToDo task
     * @param description description of the todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * A public toString method to add the task type [T] in front of the checkbox
     * @return the string representation of a todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
