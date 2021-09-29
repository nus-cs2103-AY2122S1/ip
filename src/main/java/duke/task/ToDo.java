package duke.task;

/**
 * Todo is a task without any date/time attached to it.
 */
public class ToDo extends Task {
    /**
     * Creates a ToDo with the provided description.
     * @param description The description of the ToDo.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Converts the ToDo into a String that represents the ToDo.
     * @return The String representation of a ToDo.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s", TaskType.TODO.toString(), super.toString());
    }

    /**
     * Converts the Event into a String to be stored in Storage.
     * @return String to be stored
     */
    @Override
    public String toStorageString() {
        String taskString = super.toStorageString();
        return TaskType.TODO.toString()
                + Task.STORAGE_STRING_DELIMITER
                + taskString;
    }
}
