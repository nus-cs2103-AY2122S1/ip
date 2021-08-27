package duke.command;

import duke.exception.DukeException;

import duke.taskTypes.Task;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class DeleteCommand extends Command {
    private final String chosenList;

    public DeleteCommand(Storage storage, TaskList taskList, Ui ui, String chosenList) {
        super(storage, taskList, ui);
        this.chosenList = chosenList;
    }

    @Override
    public boolean exec() throws DukeException {
        Task deleteSuccess = taskList.delete(chosenList);
        ui.taskDeleted(deleteSuccess, taskList);
        storage.saveUpdate(taskList);
        return true;
    }
}
