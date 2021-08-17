/**
 * A Task of type "Todo"
 */
public class Todo extends Task {

    /**
     * Constructor of the class
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
