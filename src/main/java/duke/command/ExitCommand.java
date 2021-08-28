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

    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Exits Duke program.
     *
     * @param taskHandler TaskHandler of Duke.
     * @param ui User interface.
     * @param storage Storage for Duke.
     */
    @Override
    public void execute(TaskHandler taskHandler, Storage storage, Ui ui) {
        ui.printExitMessage();
    }
}