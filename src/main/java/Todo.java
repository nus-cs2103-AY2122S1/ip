import java.util.Scanner;

/**
 * Class to represent a to-do task.
 * A to-do event does not have any time or date requirements.
 * It essentially represents a standard task
 *
 * @author Houten Teo
 * @version CS2103T week 2
 */
public class Todo extends Task{

    private String taskType = "Todo";
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
        super(description, isDone);
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
     * @return the corresponding status icon depending on the
     *          completion status of the task.
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
    public void markComplete() {
        if (isDone) {
            System.out.println("`" + this.description.substring(1) + "`" + " is already completed.");
        } else {
            this.isDone = true;
            System.out.println(
                    "Finally! Took you long enough to complete:" + this.description
            );
        }
    }
}
