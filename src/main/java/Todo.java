/**
 * A task of type todo
 */
public class Todo extends Task {

    /**
     * constructor of the class
     * @param description description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * The string representation of todo
     * @return string representation of todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}