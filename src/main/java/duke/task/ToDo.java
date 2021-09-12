package duke.task;

/**
 * The ToDo class encapsulates information
 * and methods pertaining to ToDo tasks.
 *
 * @author Frederick Pek
 * @version CS2103 AY21/22 Sem 1 iP
 */
public class ToDo extends Task {
    /**
     * Creates and initalizes a new ToDo task with the description.
     *
     * @param description The description of the task.
     * @return A new ToDo object.
     */
    public ToDo(String description) {
        super(description);
        this.type = "ToDo";
    }

    /**
     * Returns the format a user should use to creating this task with Duke.
     *
     * @return Returns the format a user should use to creating this task with Duke.
     */
    public String getFormat() {
        return "todo <description>";
    }
}