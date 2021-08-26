package saber.ui;

import saber.task.Task;

/**
 * A class to encapsulate the UI corresponding to DoneCommand
 */
public class DoneUI extends SaberCommandUI {
    protected String successMessage;

    protected final String unableToFindTaskError = "      I'm really sorry, Master.\n"
            + "      I'm ... not sure which task you want me\n"
            + "      to mark as done...\n";

    protected final String argumentError =  "      I'm really sorry, Master.\n"
            + "      I'm unable to find the task that\n"
            + "      you specified...\n";

    /**
     * A constructor for DoneUI which will initialize the success message to an empty string
     */
    public DoneUI() {
        this.successMessage = "";
    }

    /**
     * Set the success message for the UI
     * @param task the task to be set as done
     */
    public void setSuccessMessage(Task task){
        this.successMessage = "      Understand, Master.\n" + "      I'll mark this done right away.\n"
                + "\n        " + task + "\n";
    }

    /**
     * Print out the success message
     */
    public void showSuccess() {
        System.out.println(successMessage);
    }

    /**
     * Print out the unable to find task error when the task index given is not valid
     */
    public void showUnableToFindTaskError() {
        System.out.println(unableToFindTaskError);
    }

    /**
     * Print out the argument error when argument is invalid (not an integer or the user does not supply an argument)
     */
    public void showArgumentError() {
        System.out.println(argumentError);
    }
}
