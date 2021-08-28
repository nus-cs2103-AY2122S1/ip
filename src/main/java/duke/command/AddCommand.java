package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Adds a task to the user's list of task.
 */
public abstract class AddCommand implements Command {
    protected Task task;

    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.add(task);
        int numTasksRemaining = tasks.numTasks();
        ui.showTaskAddedMessage(task, numTasksRemaining);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
