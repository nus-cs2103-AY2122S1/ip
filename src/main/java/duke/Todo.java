package duke;

/**
 * The Todo class that represents a task to be done.
 */
public class Todo extends Task {
    /**
     * Constructor for a Todo object.
     *
     * @param desc The description of the task.
     */
    public Todo(String desc) {
        super(desc, "T");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
