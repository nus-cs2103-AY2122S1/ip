package saber.ui;

/**
 * A class to encapsulate the UI corresponding to ByeCommand
 */
public class ByeUI extends SaberCommandUI {
    protected final String successMessage = "      Am I ... no longer needed, Master?\n" +
            "      I understand. I shall excuse myself.\n";

    /**
     * Print out the success message (in this case, the goodbye message)
     */
    public void showSuccess() {
        System.out.println(successMessage);
    }
}
