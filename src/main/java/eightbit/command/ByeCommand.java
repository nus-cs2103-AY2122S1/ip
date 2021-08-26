package eightbit.command;

import eightbit.util.Storage;
import eightbit.util.TaskList;
import eightbit.util.Ui;

/**
 * Represents a command to exit the program.
 */
public class ByeCommand extends Command {

    /**
     * Terminates the program.
     *
     * @param taskList User's list of tasks.
     * @param ui Ui responsible for printing messages.
     * @param storage Storage responsible for reading/writing the file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        this.isExit = true;
    }
}
