package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to undo the previous command.
 *
 * @author Cheong Yee Ming
 * @version Duke Level-10
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
    public String runCommand() throws DukeException {
        String undoMsg = ui.guiUndoMessage();
        undoMsg += command.runCommand();
        return undoMsg;
    }
}
