package saber.ui;

import saber.task.Task;

/**
 * A class to encapsulate the UI corresponding to TodoCommand
 */
public class TodoUI extends SaberCommandUI {
    protected String successMessage;

    protected final String missingDescriptionError = "      I'm sorry, Master.\n"
            + "      What ... exactly do you want me to add\n"
            + "      to your Todo list?\n";

    /**
     * A constructor for TodoUI which will initialize the success message to an empty string
     */
    public TodoUI() {
        this.successMessage = "";
    }

    /**
     * Set the success message for the UI
     * @param todo the todo to be added
     * @param totalTask the total task available in the TaskList
     */
    public void setSuccessMessage(Task todo, int totalTask) {
        String taskPlural = totalTask <= 1 ? " task" : " tasks";

        this.successMessage = "      Yes, Master.\n"
                + "      I'll add the following to your Todo list:\n"
                + "\n        " + todo + "\n"
                + "\n      Currently, Master has " + totalTask + taskPlural
                + "\n      in the list." + "\n";
    }

    /**
     * Print out the success message
     */
    public void showSuccess() {
        System.out.println(successMessage);
    }

    /**
     * Print out the missing description error when there is no description supplied to the todo command
     */
    public void showMissingDescriptionError() {
        System.out.println(missingDescriptionError);
    }
}
