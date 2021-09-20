package duke.command;

import duke.Archive;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Type of Command that closes out of Duke.
 *
 */
public class ExitCommand extends Command {

    /**
     * Constructor.
     */
    public ExitCommand() {
    }

    /**
     * Executes operations to close out of Duke.
     *  @param taskList
     * @param ui
     * @param storage
     * @param archive
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage, Archive archive) {
        return ui.exit();
    }

    /**
     * Is an Exit Command
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
