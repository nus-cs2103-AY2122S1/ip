package duke.commands;

import duke.functionality.Storage;
import duke.functionality.Ui;

/**
 * Represents the command created when an unknown user command is encountered.
 */
public class UnknownCommand extends Command {

    public UnknownCommand (){}

    /**
     * Executes the command and simply informs the user of the invalid command.
     *
     * @param storage The storage system of the bot involved with this command.
     * @param ui The ui of the bot involved with this command.
     * @return Message to inform user that an invalid command has been input.
     */
    public String execute(Storage storage, Ui ui) {
        return ui.unknownCommandMessage();
    }

}
