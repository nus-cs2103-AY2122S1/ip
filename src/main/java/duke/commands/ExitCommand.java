package duke.commands;

import duke.functionality.Storage;
import duke.functionality.Ui;

/**
 * Represents the command when the user wants to end the bot.
 */
public class ExitCommand extends Command {
    public ExitCommand() {}

    /**
     * Executes the command and stops the bot.
     *
     * @param storage The storage system of the bot involved with this command.
     * @param ui The ui of the bot involved with this command.
     */
    public void execute(Storage storage, Ui ui) {
        ui.closeReader();
        storage.saveToFile();
        ui.goodbyeMessage();
    }

    public boolean isExit() {
        return true;
    }

}
