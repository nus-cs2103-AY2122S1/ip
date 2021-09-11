package duke.task;

import duke.exception.DukeException;

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
     Returns a new instance of ToDo with updated values.
     *
     * @param taskDescription The description of the updated ToDo.
     * @param dateAndTime A given date and time, invalid if not null.
     * @return A ToDo with the updated description.
     * @throws DukeException If a date and time was given.
     */
    public ToDo update(String taskDescription, String dateAndTime) throws DukeException {
        String updatedDescription = taskDescription == null
                ? this.taskDescription
                : taskDescription;
        if (dateAndTime != null) {
            throw new DukeException("Sorry kid, A todo cannot have a date or time.");
        } else {
            return new ToDo(updatedDescription, this.isDone);
        }
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
