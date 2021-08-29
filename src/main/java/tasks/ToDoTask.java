package tasks;

/**
 * This is a ToDoTask class, which inherits from Task.
 */
public class ToDoTask extends Task {
    public static final String KEYWORD = "[Todo]";

    /**
     * Constructor for ToDoTask.
     * @param taskName The description of the task.
     */
    public ToDoTask(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return KEYWORD + " " + super.toString();
    }
}
