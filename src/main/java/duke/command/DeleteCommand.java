package duke.command;

import duke.exception.OutOfRangeException;
//
import duke.storage.Storage;
//
import duke.task.Task;
import duke.task.TaskList;
//
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private String response;

    public DeleteCommand(String response) {
        this.response = response;
    }

    /**
     * Shows the task just be deleted.
     *
     * @param tasks The list of tasks.
     * @param ui The user interaction instance.
     * @param storage The instance to store data.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws OutOfRangeException {
        //check with the special response "delete X", where X is index of deleted item.
        int curr = Integer.parseInt(response.substring(7));
        Task shouldDelete = tasks.removeElement(curr - 1);
        storage.replace(curr - 1, null);
        ui.showDelete(shouldDelete, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
