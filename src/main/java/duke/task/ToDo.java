package duke.task;

/**
 * A task to be marked as done.
 */
public class ToDo extends Task {

    /**
     * Initialises a new instance of ToDo.
     *
     * @param taskDescription The description of the Todo task.
     * @param isDone Marks the Todo task as done or not done.
     */
    public ToDo(String taskDescription, boolean isDone) {
        super(taskDescription, isDone);
    }

    /**
     * Returns the String representation of the ToDo task.
     *
     * @return The String representation of the task.
     */
    @Override
    public String toString() {
        if (super.isDone) {
            return "[T][X] " + super.taskDescription;
        } else {
            return "[T][ ] " + super.taskDescription;
        }
    }

    /**
     * Returns a String representation of the task to store within the save file.
     *
     * @return The String representation of the task.
     */
    @Override
    public String parseToString() {
        String result = super.isDone ? "X" : "0";
        result += "T/" + super.taskDescription;
        return result;
    }
}
