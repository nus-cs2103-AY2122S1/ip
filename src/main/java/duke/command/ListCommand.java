package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class ListCommand extends Command {

    public ListCommand(TaskList taskList, Storage storage, Ui ui) {
        super(taskList, storage, ui);
    }
    @Override
    public void execute() {
        if (taskList.getSize() == 0) {
            ui.setMessage("No tasks in list!");
        } else {
            ui.setMessage(taskList.toString());
        }
    }

}
