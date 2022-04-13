package hyddd.command;

import hyddd.uimanager.TextUi;

/**
 * @@author Hang Zelin
 *
 * ByeCommand will handle the situation when a user wants to end the dialog.
 */
public class ByeCommand extends Command {
    private final TextUi textUi;

    /**
     * Constructor for ByeCommand class.
     *
     * @param textUi hyddd's UI.
     */
    public ByeCommand(TextUi textUi) {
        this.textUi = textUi;
    }

    /**
     * Returns hyddd's response when user wants to say goodbye.
     *
     * @return hyddd's response.
     */
    @Override
    public String returnResponse() {
        return goodbye();
    }

    private String goodbye() {
        return textUi.goodbyeMessage();
    }
}
