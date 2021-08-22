package duke.task;

/**
 * Encapsulates a duke.task to be done.
 */
public class ToDos extends Task {

    /**
     * Constructor for a duke.task.ToDos object.
     *
     * @param description Description of the duke.task.
     */
    public ToDos(String description) {
        super(description);
    }

    @Override
    public String saveData() {
        return "todo " + super.saveData();
    }


    /**
     * Returns the string form of the duke.task.ToDos object.
     *
     * @return The string form of the duke.task.ToDos.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
