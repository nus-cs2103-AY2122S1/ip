package misaki.tasks;

/**
 * Represents a task that has no due date.
 *
 * @author Chen Hsiao Ting
 * @version CS2103T AY21/22 Semester 1
 */
public class Todo extends Task {

    /**
     * A constructor for the to-do task.
     *
     * @param description User input task description.
     * @param isDone      Status of the task.
     */
    public Todo(String description, Boolean isDone) {
        super(description, isDone, "T");
    }

    /**
     * Returns the status and description of the to-do task.
     *
     * @return String representation of the to-do task.
     */
    public String getTask() {
        return "[T]" + "[" + getStatusIcon() + "] " + super.getDescription();
    }
}
