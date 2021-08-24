package Command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DeleteTaskCommand extends Command {

    public DeleteTaskCommand(String taskIndex) {
        super(taskIndex);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.delete(Integer.parseInt(super.getExtraInput()) - 1);
        ui.showDeleteTask(taskList);
        ui.showList(taskList);
    }
}
