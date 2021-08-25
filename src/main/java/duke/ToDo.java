package duke;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the character representing the task type.
     *
     * @return Character representing task type.
     */
    @Override
    public char getTaskType() { return 'T'; }

    /**
     * Returns the date of the todo as a String.
     *
     * @return Date of the event.
     */
    @Override
    public String getTime() {
        return "";
    }

    /**
     * Returns the string representation of a todo Object.
     *
     * @return String representation of todo Object.
     */
    @Override
    public String toString() {
        return "  [T]" + super.toString();
    }
}
