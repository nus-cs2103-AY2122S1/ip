package botto.command;

import botto.BottoException;
import botto.util.Dialog;
import botto.util.Storage;
import botto.util.TaskList;

/**
 * Botto bot's command interface.
 */
public interface Command {

    /**
     * Execute the command.
     *
     * @param taskList the task list involved.
     * @param dialog the dialog of the Botto bot.
     * @param storage storage of the Botto bot.
     * @throws BottoException when there is an error during the execution.
     */
    public void execute(TaskList taskList, Dialog dialog, Storage storage) throws BottoException;

    /**
     * Return true when it is an exit command, else false.
     *
     * @return true when it is an exit command, else false.
     */
    public default boolean isExit() {
        return false;
    }
}
