package Command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exceptions.DukeException;

public class DeleteTaskCommand extends Command {

    public DeleteTaskCommand(String taskIndex) {
        super(taskIndex);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.delete(Integer.parseInt(super.getExtraInput()) - 1);
        ui.showDeleteTask(taskList);
        ui.showList(taskList);
    }
}
