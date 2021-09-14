package duke.command;

import duke.uimanager.TextUi;

/**
 * @@author Hang Zelin
 *
 * ByeCommand will handle the situation when a user wants to read the brief user guide.
 */
public class HelpCommand extends Command {
    private final TextUi textUi;

    /**
     * Constructor for HelpCommand class.
     *
     * @param textUi Duke's UI.
     */
    public HelpCommand(TextUi textUi) {
        this.textUi = textUi;
    }

    /**
     * Returns Duke's user guide.
     *
     * @return Duke's response.
     */
    @Override
    public String returnResponse() {
        return helpMessage();
    }

    private String helpMessage() {
        return textUi.helpMessage();
    }
}
