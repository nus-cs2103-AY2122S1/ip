package duke.task;

/**
 * Represents a Task of type Event.
 */
public class Todo extends Task {
    public Todo(String todoName) {
        super(todoName);
    }

    /**
     * Returns the name of this Todo object.
     *
     * @return The Todo name.
     */
    public String getTodoName() {
        return this.taskName;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
