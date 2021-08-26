package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Representation of the list command of Duke.
 */
public class ListCommand extends Command {

    /**
     * Checks if this is an exit command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the ListCommand.
     *
     * @param tasks TaskList of Tasks to be listed.
     * @param ui Ui to print to users of Duke.
     * @param storage Storage to save and load TaskList of Duke.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.print(tasks);
    }
}
