package duke.command;

import duke.uimanager.TextUi;

/**
 * @@author Hang Zelin
 *
 * ByeCommand will handle the situation when a user wants to end the dialog.
 */
public class ByeCommand extends Command{
    private final TextUi textUi;

    /**
     * Constructor for ByeCommand class.
     *
     * @param textUi Duke's UI.
     */
    public ByeCommand(TextUi textUi) {
        this.textUi = textUi;
    }

    /**
     * Returns Duke's response when user wants to say goodbye.
     *
     * @return Duke's response.
     */
    @Override
    public String returnResponse() {
        return goodbye();
    }

    private String goodbye() {
        return textUi.goodbyeMessage();
    }
}
