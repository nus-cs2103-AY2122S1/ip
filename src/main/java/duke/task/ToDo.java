package duke.task;

public class ToDo extends Task {
    /**
     * Constructs a new ToDo task.
     *
     * @param name The name of the new ToDo task.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Converts the ToDo task to the format used for saving in the storage file on the user's computer.
     *
     * @return The save format of the ToDo task.
     */
    public String toSaveFormat() {
        return "T|" + super.toSaveFormat();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
