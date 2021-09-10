package saber.ui;

import saber.task.Task;

/**
 * Encapsulates the UI corresponding to DeleteCommand
 */
public class DeleteUI extends SaberCommandUI {
    protected String successMessage;

    protected final String unableToFindTaskError = "      I'm really sorry, Master.\n"
            + "      I'm unable to find the task that\n"
            + "      you want to delete...\n";

    protected final String argumentError = "      I'm really sorry, Master.\n"
            + "      I'm ... not sure which task you want\n"
            + "      to delete...\n";

    /**
     * Constructs DeleteUI which will initialize the success message to an empty string
     */
    public DeleteUI() {
        this.successMessage = "";
    }

    /**
     * Sets the success message for the UI
     *
     * @param task the task to be deleted
     * @param totalTask the total task available in the TaskList
     */
    public void setSuccessMessage(Task task, int totalTask) {
        String taskPlural = totalTask <= 1 ? " task" : " tasks";

        this.successMessage = "      Understand, Master.\n"
                + "      I have deleted this task.\n"
                + "\n        " + task + "\n"
                + "\n      Currently, Master has " + totalTask + taskPlural
                + "\n      in the list." + "\n";
    }

    /**
     * Gets success message
     *
     * @return success message
     */
    public String getSuccessMessage() {
        return successMessage;
    }

    /**
     * Gets unable to find task error
     *
     * @return unable to find task error
     */
    public String getUnableToFindTaskError() {
        return unableToFindTaskError;
    }

    /**
     * Gets argument error
     *
     * @return argument error
     */
    public String getArgumentError() {
        return argumentError;
    }

    /**
     * Prints out the success message
     */
    public void showSuccess() {
        System.out.println(successMessage);
    }

    /**
     * Prints out the unable to find task error when the task index given is not valid
     */
    public void showUnableToFindTaskError() {
        System.out.println(unableToFindTaskError);
    }

    /**
     * Prints out the argument error when argument is invalid (not an integer or the user does not supply an argument)
     */
    public void showArgumentError() {
        System.out.println(argumentError);
    }
}
