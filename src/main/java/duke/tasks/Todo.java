package duke.tasks;

/**
 * The Todo class that represents a task.
 */
public class Todo extends Task {

    public Todo(String description, boolean done) {
        super(description, Task.Type.TODO, done);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
