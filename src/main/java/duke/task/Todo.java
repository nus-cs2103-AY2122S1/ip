package duke.task;


/**
 * The class to encapsulate a todo task.
 */
public class Todo extends Task {

    /**
     * A public constructor which calls the parent
     * constructor with the given description.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * The letter which indicates the type of task.
     *
     * @return The string containing T.
     */
    @Override
    public String getTaskIndicator() {
        return "T";
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representing the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
