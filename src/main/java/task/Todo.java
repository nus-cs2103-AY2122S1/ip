package task;

/**
 * The Todo class is a child class of Task.
 */
public class Todo extends Task {

    /**
     * Constructor for a new Todo object.
     *
     * @param description description of the Todo Task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Retrieves the description of this Todo object.
     *
     * @return string describing Todo object's status.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
