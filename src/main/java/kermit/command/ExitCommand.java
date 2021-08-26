package kermit.command;

import kermit.KermitException;
import kermit.Storage;
import kermit.TaskList;
import kermit.Ui;

/**
 * Exit command tells program to exit.
 */
public class ExitCommand extends Command {
    /**
     * Exit command constructor
     */
    public ExitCommand() {
    }

    /**
     * Executes exit command.
     * Notify user that program is exiting and saves the current state of the task list.
     *
     * @param taskList Instance of task list used.
     * @param ui       Instance of Ui used.
     * @param storage  Instance of storage class used.
     * @throws KermitException if there is an error saving the file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws KermitException {
        ui.showGoodbyeMessage();
        storage.save(taskList);
    }

    /**
     * Returns if command is the exit command.
     *
     * @return Always returns true as this is the exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}