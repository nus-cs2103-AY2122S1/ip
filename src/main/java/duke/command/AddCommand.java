package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        taskList.addTask(task);
        storage.add(task);
        ui.showAddMessage(task.toString(), taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
