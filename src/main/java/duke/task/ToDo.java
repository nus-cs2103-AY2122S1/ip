package duke.task;

public class ToDo extends Task {

    public ToDo(String taskName, boolean isDone) {
        super(taskName, isDone);
    }

    /**
     * Returns the string representation of the ToDo task.
     *
     * @return The string represenation of the task.
     */
    @Override
    public String toString() {
        if (super.isDone) {
            return "[T][X] " + super.taskName;
        } else {
            return "[T][ ] " + super.taskName;
        }
    }

    /**
     * Returns a string representation of the task to store within the save file.
     *
     * @return The string representation of the task.
     */
    @Override
    public String parseToString() {
        String result = super.isDone ? "X" : "0";
        result += "T/" + super.taskName;
        return result;
    }
}
