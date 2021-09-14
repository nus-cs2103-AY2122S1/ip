package botto.command;

import botto.util.Dialog;
import botto.util.Storage;
import botto.util.TaskList;

/**
 * Command for printing help manual
 */
public class HelpCommand implements Command {

    /**
     * Print the help manual of the bot.
     *
     * @param taskList the task list involved.
     * @param dialog the dialog of the Botto bot.
     * @param storage storage of the Botto bot.
     */
    @Override
    public void execute(TaskList taskList, Dialog dialog, Storage storage) {
        dialog.showHelp();
    }
}
