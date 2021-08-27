package duke.task;

/**
 * The Task Class is a representation of a task that Duke is keeping track of.
 *
 * It contains information relating to the task:
 * - description
 * - isDone
 *
 * @author Benedict Chua
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor for a Task.
     *
     * @param description The name of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Convert the Task into a suitable String for saving into file.
     * To be overridden by child classes to call formatString in a suitable way.
     *
     * @return String of the formatted Task.
     */
    public abstract String saveAsString();

    /**
     * Returns the status of whether the task has been completed.
     *
     * @return "X" if is completed, " " if not completed
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Checks if task is done and marks task as done if task is not done
     *
     * @return String containing the description of task if not already done, else message that action is redundant.
     */
    public String markTaskAsDone() {
        if (!this.isDone) {
            this.isDone = true;
            return "  " + this.toString();
        } else {
            return "Wait...  You've already completed this task before you dummy!";
        }
    }

    /**
     * Formats the task into a suitable String for saving.
     *
     * @return String containing formatted Task.
     */
    public String formatString(String ...args) {
        switch (args.length) {
        case 1:
            // returns type of task, completion and description as a string
            return String.format("%s | %d | %s", args[0], this.isDone ? 1 : 0, this.description);
        case 2:
            // returns additional date element
            return String.format("%s | %d | %s | %s", args[0], this.isDone ? 1 : 0, this.description, args[1]);
        default:
            // should NOT reach default as this function is only called in corresponding children of Tasks
            return null;
        }

    }

    /**
     * Checks if task is due on the given date and returns the result.
     *
     * @param date String containing the date in the form dd/mm/yyyy, dd-mm-yyyy or yyyy-mm-dd.
     * @return boolean of the result.
     */
    public boolean isOnDate(String date) {
        return false;
    }

    /**
     * Checks if tasks description contains the keyword given and returns the result.
     * Both description and keyword will be case insensitive for check.
     *
     * @param keyword String of the keyword to be checked against the description.
     * @return boolean of whether description contains the keyword.
     */
    public boolean containsKeyword(String keyword) {
        int result = this.description.toLowerCase().indexOf(keyword.toLowerCase());
        return result != -1;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }
}
