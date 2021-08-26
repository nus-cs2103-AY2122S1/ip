package saber.ui;

import saber.task.Task;

/**
 * A class to encapsulate the UI corresponding to AddCommand
 */
public class AddUI extends SaberCommandUI {
    protected String successMessage;

    protected final String missingDescriptionError = "      I'm sorry, Master.\n"
            + "      What ... exactly do you want me to add?\n";

    /**
     * A constructor for AddUI which will initialize the success message to an empty string
     */
    public AddUI() {
        this.successMessage = "";
    }

    /**
     * Set the success message for the UI
     * @param task the task to be added
     */
    public void setSuccessMessage(Task task){
        this.successMessage = "      I have added: "  + task + "\n";
    }

    /**
     * Print out the success message
     */
    public void showSuccess() {
        System.out.println(successMessage);
    }

    /**
     * Print out the missing description error when there is no description supplied to the add command
     */
    public void showMissingDescriptionError() {
        System.out.println(missingDescriptionError);
    }
}
