package duke.logic.tasks;

import duke.logic.commands.UpdateCommand.UpdateTaskDescriptor;

/**
 * Represents a to-do task.
 */
public class ToDo extends Task {

    /**
     * Creates a new to-do task.
     *
     * @param description The task description.
     * @param isDone The status of the task.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Creates a new to-do task.
     *
     * @param description The task description.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public Task createUpdatedCopy(UpdateTaskDescriptor updateDescriptor) {
        String updatedDescription = updateDescriptor.getDescription().orElse(this.getDescription());
        return new ToDo(updatedDescription, getIsDone());
    }

    @Override
    public String getSaveFormat() {
        return "T" + super.getSaveFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ToDo)) {
            return false;
        }
        ToDo other = (ToDo) obj;
        return getDescription().equals(other.getDescription()) && getIsDone() == other.getIsDone();
    }
}
