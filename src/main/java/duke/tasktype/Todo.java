package duke.tasktype;

import duke.Ui;
import duke.exception.WrongCommandFormatException;

/**
 * Class to represent a to-do task.
 * A to-do event does not have any time or date requirements.
 * It essentially represents a standard task
 *
 * @author Houten Teo
 * @version CS2103T week 3
 */
public class Todo implements Task {

    private final String taskType = "TODO";
    private String description;
    private boolean isDone;

    /**
     * Constructor for the todo task
     * @param description The description of the task.
     * @param isDone Whether the task has been compelted or not.
     * @throws WrongCommandFormatException Thrown when the user inputs the
     *                                     command with the wrong format.
     */
    public Todo(String description, boolean isDone) throws WrongCommandFormatException {
        if (description.equals("")) {
            throw new WrongCommandFormatException(
                    "No task specified. Please try again"
            );
        } else {
            this.description = description;
            this.isDone = isDone;
        }

    }

    /**
     * Overridden method to get the specific type icon .
     * @return The String representation of the icon.
     */
    @Override
    public String getTypeIcon() {
        return "[T]";
    }

    /**
     * Overridden method to get the status icon.
     * @return The corresponding status icon depending on the
     *         completion status of the task.
     */
    @Override
    public String getStatusIcon() {
        if (isDone) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    /**
     * Method to get the description of the to do.
     * @return The description.
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Overridden toString method to display the String representation
     * of the task.
     * @return The type icon, status icon and description arranged in a line.
     */
    @Override
    public String toString() {
        String typeIcon = getTypeIcon();
        String statusIcon = getStatusIcon();
        return typeIcon
                + " "
                + statusIcon
                + this.description;
    }

    /**
     * Overridden method to mark the task as complete.
     * If the task has already been completed, inform the user.
     * Else indicate that the task has been correctly marked.
     */
    @Override
    public String markComplete() {
        if (isDone) {
            return Ui.getTaskAlrCompletedMessage(this);
        } else {
            this.isDone = true;
            return Ui.getMarkCompleteTodoMessage(this);
        }
    }

    /**
     * Method to return the string representation of the to do to be stored in Data.txt.
     * @return
     */
    @Override
    public String createData() {
        String data = getTypeIcon()
                + ""
                + getStatusIcon()
                + " "
                + this.description;
        return data;
    }

    /**
     * Method to return the task type.
     * @return The string representation of the task type.
     */
    @Override
    public String getTaskType() {
        return this.taskType;
    }

    /**
     * Method to check if the task has been marked as completed.
     * @return True if the task is completed and false otherwise.
     */
    @Override
    public boolean isDone() {
        return this.isDone;
    }
}
