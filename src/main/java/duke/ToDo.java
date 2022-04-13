package duke;

/**
 * A type of Task. Inherits from Task, does not have any date/time attached to it.
 */
public class ToDo extends Task {
    /**
     * Constructor for ToDo.
     * Takes in the description of the ToDo.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructor for ToDo.
     * Takes in the description and the status of the task.
     *
     * @param description The description of the task.
     * @param isDone Indicates if the task is done.
     */
    public ToDo(String description, Boolean isDone) {
        this(description);
        super.setDone(isDone);
    }

    /**
     * Returns the string representation of the class.
     *
     * @return String representation of the class.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string formatted for writing into file.
     *
     * @return String representation of the task for file writing.
     */
    @Override
    public String saveString() {
        return "T," + super.saveString();
    }
}
