package ponyo.commands;

import ponyo.data.task.TaskList;
import ponyo.ui.Ui;
import ponyo.storage.Storage;

public class DoneCommand extends Command {
    private final int taskToMarkDone;

    public DoneCommand(int taskToMarkDone) {
        this.taskToMarkDone = taskToMarkDone;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.retrieveTask(taskToMarkDone - 1).markAsDone();
        storage.getFullContents(tasks);
        ui.show("\tNice! I've marked this task as done: \n\t\t" + tasks.retrieveTask(taskToMarkDone - 1));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
