package duke.task;

/**
 * Represents a Task of type Event.
 */
public class Todo extends Task{
    public Todo(String todoName) {
        super(todoName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
