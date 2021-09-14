package duke.command;

import duke.data.Storage;
import duke.data.InformationList;
import duke.data.Ui;

/**
 * Command that displays a brief summary of the features of Cynthius.
 */
public class HelpCommand extends Command {
    /**
     * Displays a guide on how to use each keyword.
     *
     * @param tasks   Not used for this command.
     * @param ui      The ui that sends the information as a string to the user.
     * @param storage Not used for this command.
     * @return The message produced by ui.
     */
    @Override
    public String execute(InformationList tasks, Ui ui, Storage storage) {
        return Ui.showHelp();
    }
}