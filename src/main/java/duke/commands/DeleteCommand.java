package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.util.Storage;
import duke.util.Ui;

public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructor for a delete command.
     *
     * @param index Integer reference for the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui) {
        assert index < tasks.getNumOfTasks();
        assert index > 0;

        Task deleted = tasks.getTask(index);
        tasks.deleteTask(index);
        Storage.writeDatabase(tasks);
        return ui.deleteTaskOutput(deleted);
    }
}
