package bot.tasks;

/**
 * Represents a task without any date/time attached to it.
 */
public class ToDo extends Task {

    /**
     * Creates a ToDo with a description.
     *
     * @param description The description of this ToDo.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a String representing this task that is shown to the user.
     *
     * @return A String representing this task.
     */
    @Override
    public String toListFormat() {
        return String.format("T | %d | %s\n",
                this.getStatusIcon().equals("X") ? 1 : 0,
                this.getDescription());
    }

    /**
     * Returns this task's status and description.
     *
     * @return A string showing what the task is and its description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
