/**
 * The general task class
 *
 * @Author Houten Teo
 * @version CS2103T week 2
 */

public class Task {

    private String taskName;
    private boolean isDone;

    /**
     * Constructor for the Task class
     * @param taskName Name of the task.
     * @param isDone True if the task is completed and false otherwise.
     */
    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    /**
     * Method to mark the task as complete.
     * Additionally, prints out the taskName to allow user to validate.
     */
    public void markComplete() {
        if (isDone) {
            System.out.println("`" + taskName + "`" + " is already completed.");
        } else {
            this.isDone = true;
            System.out.println(
                    "Finally! Took you long enough to complete:" + taskName
            );
        }
    }

    /**
     * Method to return the corresponding status icon depending on
     * whether the task has been completed or not.
     * @return The String representation of the icon.
     */
    public String getStatusIcon() {
        if (isDone) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    /**
     * Method to return the corresponding type icon depending on
     * the type of the task.
     * @return
     */
    public String getTypeIcon() {
        return "[ ]";
    }
}
