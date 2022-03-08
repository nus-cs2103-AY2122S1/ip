package duke.command;

import duke.util.Storage;
import duke.util.TaskHandler;
import duke.util.Ui;

/**
 * This class encapsulates the exit command.
 *
 * @author Teo Sin Yee
 */
public class ExitCommand extends Command {

    /**
     * Exits Duke program.
     *
     * @param taskHandler TaskHandler of Duke.
     * @param ui User interface.
     * @param storage Storage for Duke.
     * @return Message to be shown to user.
     */
    @Override
    public String execute(TaskHandler taskHandler, Storage storage, Ui ui) {
        return Ui.getExitMessage();
    }
}
