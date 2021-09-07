package duke;

/**
 * A subclass of Task that represents a Todo task.
 *
 */
public class ToDos extends Task {
    private String type;

    /**
     * Constructor for Deadline class.
     *
     * @param title Name of the task to be created.
     *
     */
    public ToDos(String title) {
        super(title);
        type = "T";
    }

    @Override
    public String toString() {
        return "[" + type + "]" + super.toString();
    }

    /**
     * A method that prints out details of a todo task.
     *
     * @return Details of todo task.
     */
    @Override
    public String writeTask() {
        return type + " | " + super.writeTask();
    }
}
