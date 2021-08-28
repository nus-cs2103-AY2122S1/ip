package duke;

/**
 * Todo class that extends from Task class
 */
public class Todo extends Task {
    /**
     * Creates a Todo object
     * @param description takes in a String representing the task
     */
    public Todo(String description){
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
