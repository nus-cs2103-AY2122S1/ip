package duke.command;

import duke.ui.Ui;

/**
 * Invalid command.
 */
public class CommandInvalid extends Command {

    private final String input;

    /**
     * Constructor for an invalid command.
     *
     * @param input Input that caused the error to occur.
     */
    public CommandInvalid(String input) {
        this.description = "";
        this.commandName = "";
        this.arguments = new String[]{};
        this.input = input;
    }

    /**
     * Displays error message.
     */
    @Override
    public String execute() {
        return Ui.messageInvalidCommand(input);
    }
}
