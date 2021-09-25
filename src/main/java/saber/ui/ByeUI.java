package saber.ui;

/**
 * Encapsulates the UI corresponding to ByeCommand
 */
public class ByeUI extends SaberCommandUI {
    protected final String successMessage = "      Am I ... no longer needed, Master?\n"
            + "      I understand. I shall excuse myself.\n";

    /**
     * Prints out the success message (in this case, the goodbye message)
     */
    public void showSuccess() {
        System.out.println(successMessage);
    }

    /**
     * Gets success message
     *
     * @return success message
     */
    public String getSuccessMessage() {
        return successMessage;
    }
}
