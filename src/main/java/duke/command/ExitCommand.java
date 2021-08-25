package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.command.Command;

/**
 * Representation for exit command of Duke.
 */
public class ExitCommand extends Command {

    /**
     * Checks if this is an exit command.
     *
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Execute the Exit Command.
     *
     * @param taskList TaskList of Tasks.
     * @param ui Ui to print to users of Duke.
     * @param storage Storage to save and load TaskList of Duke.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        storage.saveFile(taskList);
        ui.exit();
    }
}
