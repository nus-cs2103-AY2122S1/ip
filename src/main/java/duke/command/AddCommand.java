package duke.command;

import duke.FileManager;
import duke.Tasklist;
import duke.Ui;
import duke.task.Task;

/**
 * A command which aims to add a task.
 */
public class AddCommand extends Command {
    private Task toAddTask;

    /**
     * Makes a AddCommand that adds the task that was inputted.
     *
     * @param toAddTask the task to be added.
     */
    public AddCommand(Task toAddTask) {
        this.toAddTask = toAddTask;
    }

    /**
     * Adds the task to current list of task.
     *
     * @param tasks current list of task.
     * @param ui the ui to interact with the user.
     * @param fileManager the filemanger that manages the storage of duke.
     */
    @Override
    public void execute(Tasklist tasks, Ui ui, FileManager fileManager) {
        tasks.add(this.toAddTask);
        fileManager.updateTaskList(tasks, ui);
        ui.taskAdded(this.toAddTask, tasks.getNumTasks());
    }

    /**
     * Returns if the function is a exit command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return toAddTask.toString();
    }
}
