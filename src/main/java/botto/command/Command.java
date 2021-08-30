package botto.command;

import botto.BottoException;
import botto.util.Storage;
import botto.util.TaskList;
import botto.util.Ui;

/**
 * Botto bot's command interface
 */
public interface Command {

    /**
     * execute the command
     *
     * @param taskList the task list involved
     * @param ui the ui of the Botto bot
     * @param storage storage of the Botto bot
     * @throws BottoException when there is an error during the execution
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws BottoException;

    /**
     * return true when it is an exit command, else false
     *
     * @return true when it is an exit command, else false
     */
    public default boolean isExit() {
        return false;
    }
}
