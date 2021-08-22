package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import dukeException.DukeException;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.printMessage(taskList.listAllTasks());
    }
}
