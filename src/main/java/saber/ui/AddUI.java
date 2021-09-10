package saber.ui;

import saber.task.Task;

/**
 * Encapsulates the UI corresponding to AddCommand
 */
public class AddUI extends SaberCommandUI {
    protected String successMessage;

    protected final String missingDescriptionError = "      I'm sorry, Master.\n"
            + "      What ... exactly do you want me to add?\n";

    /**
     * Constructs AddUI which will initialize the success message to an empty string
     */
    public AddUI() {
        this.successMessage = "";
    }

    /**
     * Sets the success message for the UI
     *
     * @param task the task to be added
     */
    public void setSuccessMessage(Task task) {
        this.successMessage = "      I have added: " + task + "\n";
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
     * Gets missing description error
     *
     * @return missing description error
     */
    public String getMissingDescriptionError() {
        return missingDescriptionError;
    }

    /**
     * Prints out the success message
     */
    public void showSuccess() {
        System.out.println(successMessage);
    }

    /**
     * Prints out the missing description error when there is no description supplied to the add command
     */
    public void showMissingDescriptionError() {
        System.out.println(missingDescriptionError);
    }
}
