package duke.command;


import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    private int index;
    private TaskList tasks;
    private Ui ui;
    private Storage storage;


    /**
     * Instantiates a command to delete a Task from the task list. Task is deleted based on current indexing.
     *
     * @param index Given index of the task to delete.
     * @param tasks The task list to delete task from.
     * @param ui Ui to handle interactions.
     * @param storage Storage for tasks.
     */
    public DeleteCommand(int index, TaskList tasks, Ui ui, Storage storage) {
        this.index = index;
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
    }

    @Override
    public void execute() {

        Task task = tasks.get(index);
        tasks.remove(index);
        storage.saveTasks(tasks);
        ui.delete(task, tasks);
    }

}
