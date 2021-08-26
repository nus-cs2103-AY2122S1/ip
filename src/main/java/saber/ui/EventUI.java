package saber.ui;

import saber.task.Task;

/**
 * A class to encapsulates the UI corresponding to EventCommand
 */
public class EventUI extends SaberCommandUI {
    protected final String missingDescriptionError = "      I'm sorry, Master.\n"
            + "      I'm ... not sure what event you want me\n"
            + "      to add ...\n";

    protected final String missingTimeError = "      I'm really sorry, Master.\n"
            + "      I won't be able to remind you\n"
            + "      to come to this event without\n"
            + "      knowing the time of the event...\n";

    protected final String parsingTimeError = "      I'm sorry, Master. I'm unable\n"
            + "      to understand the date you\n"
            + "      just told me. Maybe..., Master\n"
            + "      can try to use other format?\n";

    protected String successMessage;

    /**
     * A constructor for EventUI which will initialize the success message to an empty string
     */
    public EventUI() {
        this.successMessage = "";
    }

    /**
     * Set the success message for the UI
     * @param event the event task to be added
     * @param totalTask the total task available in the TaskList
     */
    public void setSuccessMessage(Task event, int totalTask) {
        String taskPlural = totalTask <= 1 ? " task" : " tasks";
        this.successMessage = "      Right, Master.\n"
                + "      I'll make sure you remember\n"
                + "      to come to this event:\n"
                + "\n        " + event + "\n"
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
     * Print out the missing description error when there is no description supplied to the event command
     */
    public void showMissingDescriptionError() {
        System.out.println(missingDescriptionError);
    }

    /**
     * Print out the missing time error when there is no time supplied to the event command
     */
    public void showMissingTimeError() {
        System.out.println(missingTimeError);
    }

    /**
     * Print the parsing time error when the time supplied to the event command is invalid
     */
    public void showParsingTimeError() {
        System.out.println(parsingTimeError);
    }
}
