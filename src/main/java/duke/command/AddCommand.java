package duke.command;

import duke.storage.Storage;
import duke.storage.TaskList;
import duke.task.Task;
import duke.ui.Ui;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        taskList.add(task);
        storage.write(taskList.getTasks());
        ui.displayAddedTask(task, taskList.getTaskCount());
    }

    public Task getTask() {
        return task;
    }
}
