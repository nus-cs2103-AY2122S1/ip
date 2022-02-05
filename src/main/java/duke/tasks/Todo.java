package duke.tasks;

/**
 * The Todo class that represents a task.
 */
public class Todo extends Task {
    /**
     * Constructor to create a todo task with no datetime.
     *
     * @param description Description of task.
     * @param done Completeness of task.
     */
    public Todo(String description, boolean done) {
        super(description, Task.Type.TODO, done);
        assert !description.trim().equals("");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
