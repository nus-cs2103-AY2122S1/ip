package ponyo.commands;

import ponyo.data.task.TaskList;
import ponyo.storage.Storage;
import ponyo.ui.Ui;

/**
 * Marks a task as done based on the inputted task index.
 */
public class DoneCommand extends Command {
    private final int taskToMarkDone;

    public DoneCommand(int taskToMarkDone) {
        this.taskToMarkDone = taskToMarkDone;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        tasks.retrieveTask(taskToMarkDone - 1).markAsDone();
        storage.getFullContents(tasks);
        Ui.show("\tNice! I've marked this task as done: \n\t\t" + tasks.retrieveTask(taskToMarkDone - 1));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
