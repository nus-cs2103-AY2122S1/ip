package duke.tasks;

/**
 * Reflects a particular task on a Todo list
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * Internal constructor called to indicate that the Todo task is completed.
     */
    private Todo(String description, Boolean isDone) {
        super(description, isDone);
    }

    /**
     * Indicates that the Todo task is completed.
     */
    public Todo markAsDone() {
        return new Todo(this.description, true);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
