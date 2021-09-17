package duke.task;

public class ToDo extends Task {

    /**
     * Constructor for new tasks as input by user.
     *
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructor for new tasks as read from saved file.
     *
     * @param description Description of the task.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Gets the task type indicator to be displayed when the
     * user lists tasks.
     *
     * @return The String representation of the type label for todos.
     */
    @Override
    public String getTypeIndicator() {
        return "[T]";
    }

    /**
     * Converts the object to a String representation to be stored
     * in file.
     *
     * @return The String representation of the file record of the object.
     */
    @Override
    public String toFileRecord() {
        return String.format("T | %d | %s",
                this.isDone ? 1 : 0 , this.description);
    }
}


