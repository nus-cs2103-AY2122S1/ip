package duke.command;

import duke.exception.DukeException;
import duke.exception.MismatchedFormException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents the deletion of the command.
 */
public class DeleteCommand extends Command {
    private String response;
    private int splitIndex;

    /**
     * Deletes the command at certain index.
     *
     * @param response The user input.
     * @param splitIndex The index where the digit and delete is split.
     */
    public DeleteCommand(String response, int splitIndex) {
        this.response = response;
        this.splitIndex = splitIndex;
    }

    /**
     * Shows the task just be deleted.
     *
     * @param tasks The list of tasks.
     * @param ui The user interaction instance.
     * @param storage The instance to store data
     * @throws DukeException The runtime exceptions occurred in Duke.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        //check with the special response "delete X", where X is index of deleted item.
        String laterPartToDelete = response.substring(splitIndex);
        if (!Parser.checkIsDigit(laterPartToDelete)) {
            throw new MismatchedFormException("delete", "Integer");
        }
        int taskNumber = Integer.parseInt(laterPartToDelete);
        Task shouldDelete = tasks.removeElement(taskNumber - 1);
        storage.replace(taskNumber - 1, null);
        return ui.showDelete(shouldDelete, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
