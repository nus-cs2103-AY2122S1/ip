/**
 * Todo class for implementing tasks that are to be done.
 */
public class Todo extends Task {
    /**
     * Constructor for the Todo class.
     * @param description name of the task to be done
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
