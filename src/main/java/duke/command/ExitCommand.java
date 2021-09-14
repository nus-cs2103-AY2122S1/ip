package duke.command;

import duke.exception.StorageSavingException;
import duke.task.TaskList;
import duke.ui.Storage;
import duke.ui.Ui;

/**
 * Represents a command to exit the program.
 *
 * @author Sherman Ng Wei Sheng
 */
public class ExitCommand extends Command {
    private static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!";
    private final boolean isExit;

    /**
     * Constructor for the exit command.
     */
    public ExitCommand() {
        this.isExit = true;
    }

    /**
     * Returns true if the command is a programme terminating command, in this case, this is a terminating command.
     *
     * @return True.
     */
    @Override
    public boolean isAExitCommand() {
        return isExit;
    }

    /**
     * Executes the command to exit the program.
     *
     * @param list TaskList before execution of the command.
     * @param ui Ui object to log the execution of the command.
     * @param storage Storage object that references the path to store the updated list of tasks.
     * @return The string to be printed.
     * @throws StorageSavingException If exception encountered when storing the list.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws StorageSavingException {
        ui.closeScanner();
        storage.saveDukeData(list.convertToStorageString());
        return ui.printAndReturnMessage(MESSAGE_EXIT);
    }
}
