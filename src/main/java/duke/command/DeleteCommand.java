package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task deletedTask = tasks.getTask(taskNumber);
        tasks.deleteTask(taskNumber);
        storage.save(tasks);
        ui.showDeleteTask(deletedTask, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
