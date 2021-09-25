package iris.command;

import iris.exception.IrisException;
import iris.storage.Storage;
import iris.task.TaskList;
import iris.ui.Ui;

/**
 * Command to undo the previous command.
 *
 * @author Cheong Yee Ming
 * @version Iris Level-10
 */
public class UndoCommand extends Command {

    private final Command command;

    /**
     * Constructor for an UndoCommand.
     *
     * @param taskList Handles all operations regarding tasks.
     * @param storage  Save and load data from local directory.
     * @param ui       Prints message with respect to user input.
     * @param command  Command to be executed to revert changes.
     */
    public UndoCommand(TaskList taskList, Storage storage, Ui ui, Command command) {
        super(taskList, storage, ui);
        this.command = command;
    }

    /**
     * Executes runCommand.
     * Undo the previous command
     *
     * @return String representation undo command.
     */
    @Override
    public String runCommand() throws IrisException {
        String undoMsg = ui.guiUndoMessage();
        undoMsg += command.runCommand();
        return undoMsg;
    }
}
