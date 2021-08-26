package tasks;

public class Todo extends Task {

    /**
     * A constructor for the to-do task.
     *
     * @param description user input task description.
     * @param isDone status of the task.
     */
    public Todo(String description, Boolean isDone) {
        super(description, isDone, "T");
    }

    /**
     * Returns the status and description of the to-do task.
     *
     * @return a string representation of the to-do task.
     */
    public String getTask() {
        return "[T]" + "[" + getStatusIcon() + "] " + super.getDescription();
    }
}
