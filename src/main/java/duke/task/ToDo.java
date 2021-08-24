package duke.task;

import duke.task.Task;
import duke.task.TaskType;

/**
 * Todo is a task without any date/time attached to it.
 */
public class ToDo extends Task {
    /**
     * Creates a duke.task.ToDo with the provided description.
     * @param description The description of the duke.task.ToDo.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Converts the duke.task.ToDo into a String that represents the duke.task.ToDo.
     * @return The String representation of a duke.task.ToDo.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s", TaskType.TODO.toString(), super.toString());
    }

    /**
     * Converts the duke.task.Event into a String to be stored in duke.Storage.
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
