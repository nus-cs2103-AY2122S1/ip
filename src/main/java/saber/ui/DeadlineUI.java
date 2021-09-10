package saber.ui;

import saber.task.Task;

/**
 * Encapsulates the UI corresponding to DeadlineCommand
 */
public class DeadlineUI extends SaberCommandUI {
    protected final String missingDescriptionError = "      I'm really sorry, Master.\n"
            + "      I'm ... not sure what task you want me\n"
            + "      to add ...\n";

    protected final String missingTimeError = "      I'm sorry, Master.\n"
            + "      I won't be able to ensure that you\n"
            + "      do the task on time without\n"
            + "      knowing the deadline...\n";

    protected final String parsingTimeError = "      I'm sorry, Master. I'm unable\n"
            + "      to understand the date you\n"
            + "      just told me. Maybe..., Master\n"
            + "      can try to use other format?\n";

    private String successMessage;

    /**
     * Constructs DeadlineUI which will initialize the success message to an empty string
     */
    public DeadlineUI() {
        this.successMessage = "";
    }

    /**
     * Sets the success message for the UI
     *
     * @param deadline the deadline task to be added
     * @param totalTask the total task available in the TaskList
     */
    public void setSuccessMessage(Task deadline, int totalTask) {
        String taskPlural = totalTask <= 1 ? " task" : " tasks";

        this.successMessage = "      Right, Master.\n"
                + "      I'll ensure that you will\n"
                + "      do this task before the deadline:\n"
                + "\n        " + deadline + "\n"
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
     * Gets missing description error
     *
     * @return missing description error
     */
    public String getMissingDescriptionError() {
        return missingDescriptionError;
    }

    /**
     * Gets missing time error
     *
     * @return missing time error
     */
    public String getMissingTimeError() {
        return missingTimeError;
    }

    /**
     * Gets parsing time error
     *
     * @return parsing time error
     */
    public String getParsingTimeError() {
        return parsingTimeError;
    }

    /**
     * Prints out the success message
     */
    public void showSuccess() {
        System.out.println(successMessage);
    }

    /**
     * Prints out the missing description error when there is no description supplied to the deadline command
     */
    public void showMissingDescriptionError() {
        System.out.println(missingDescriptionError);
    }

    /**
     * Prints out the missing time error when there is no time supplied to the deadline command
     */
    public void showMissingTimeError() {
        System.out.println(missingTimeError);
    }

    /**
     * Prints the parsing time error when the time supplied to the deadline command is invalid
     */
    public void showParsingTimeError() {
        System.out.println(parsingTimeError);
    }
}
