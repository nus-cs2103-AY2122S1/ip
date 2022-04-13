package duke.commands;

import duke.gui.Ui;
import duke.storage.Storage;

/**
 * Class to handle the list command.
 */
public class ListCommand extends Command {

    /**
     * Prints out current list of task.
     *
     * @param ui The Ui instance for printing.
     * @param storage The Storage instance to get the list.
     * @return String to represent the reply of Duke.
     */

    @Override
    public String execute(Ui ui, Storage storage) {
        return ui.reply(storage.getList());
    }
}
