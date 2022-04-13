package duke.task;

/**
 * Encapsulates a task to be done.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDos object.
     *
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the ToDos object as data for saving.
     *
     * @return Todos object save data.
     */
    @Override
    public String saveData() {
        return "todo " + super.saveData();
    }


    /**
     * Returns the string form of the ToDos object.
     *
     * @return The string form of the ToDos.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
