package duke;

/**
 * Represents a task with only a description and no stated deadline or scheduled time.
 */
public class ToDo extends Task {
    ToDo(String description) {
        super(description);
    }

    ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the type of task. Always return "T" which stands of the "T" in ToDo.
     *
     * @return "T".
     */
    @Override
    public String getTypeOfTask() {
        return "T";
    }

    /**
     * Returns the ToDo object in a string format suitable for storing in file.
     *
     * @return String of the ToDo object in the correct format for storing in file.
     */
    @Override
    public String saveTaskToFile() {
        return this.getTypeOfTask() + "||" + this.getStatusIcon() + "||" + this.getDescription();
    }

    /**
     * Returns the ToDo object in a string format.
     *
     * @return String in the format of "[T][marked as done?]_description."
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.getTypeOfTask(),this.getStatusIcon(), this.getDescription());
    }
}
