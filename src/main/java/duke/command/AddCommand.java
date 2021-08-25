package duke.command;

import duke.FileManager;
import duke.Tasklist;
import duke.Ui;
import duke.task.Task;

public class AddCommand extends Command {
    private Task toAddTask;

    public AddCommand(Task toAddTask) {
        this.toAddTask = toAddTask;
    }

    @Override
    public void execute(Tasklist tasks, Ui ui, FileManager fileManager) {
        tasks.add(this.toAddTask);
        fileManager.updateTaskList(tasks, ui);
        ui.taskAdded(this.toAddTask, tasks.getNumTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return toAddTask.toString();
    }
}
