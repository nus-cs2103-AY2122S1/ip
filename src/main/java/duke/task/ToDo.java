package duke.task;

public class ToDo extends Task {

    public ToDo(String taskName, boolean isDone) {
        super(taskName, isDone);
    }

    /**
<<<<<<< HEAD
     * Returns the String representation of the ToDo task.
     *
     * @return The String representation of the task.
=======
     * Returns the string representation of the ToDo task.
     *
     * @return The string represenation of the task.
>>>>>>> Level-9
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
<<<<<<< HEAD
     * Returns a String representation of the task to store within the save file.
     *
     * @return The String representation of the task.
=======
     * Returns a string representation of the task to store within the save file.
     *
     * @return The string representation of the task.
>>>>>>> Level-9
     */
    @Override
    public String parseToString() {
        String result = super.isDone ? "X" : "0";
        result += "T/" + super.taskName;
        return result;
    }
}
