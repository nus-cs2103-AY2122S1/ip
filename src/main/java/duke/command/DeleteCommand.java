package duke.command;

import duke.exception.OutOfRangeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws OutOfRangeException {
        //check with the special response "delete X", where X is index of deleted item.
        int taskNumber = Integer.parseInt(response.substring(7));
        assert taskNumber > 0;
        Task shouldDelete = tasks.removeElement(taskNumber - 1);
        assert shouldDelete != null;
        storage.replace(taskNumber - 1, null);
        return ui.showDelete(shouldDelete, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
