package duke.command;

import duke.data.Storage;
import duke.data.InformationList;
import duke.data.Ui;

/**
 * Command that exits Cynthius when executed.
 */
public class ExitCommand extends Command {

    /**
     * Saves the current information to disk and displays exit message.
     *
     * @param information The list of information that a user has.
     * @param ui The ui that sends a message to the user once the tasks are saved.
     * @param storage Saves the updated InformationList to disk.
     * @return The message produced by ui.
     */
    @Override
    public String execute(InformationList information, Ui ui, Storage storage) {
        storage.save(information);
        return ui.showGoodbye();
    }
}
