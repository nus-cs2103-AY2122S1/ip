package duke;

/**
 * Encapsulates a to-do in the task list.
 */
public class Todo extends Task {

    /**
     * Creates the to-do.
     *
     * @param description the description of this task..
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Formats the task into a string that is compliant with the save file format.
     *
     * @return a string representing the task to be saved in save file
     */
    public String saveData() {
        return "T | " + this.getStatusIcon() + " | " + description;
    }
}
