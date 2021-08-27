package duke.command;

import duke.data.exceptions.DukeException;
import duke.data.exceptions.InvalidInputException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    private Task deleteTask(TaskList tasks) throws InvalidInputException {
        int taskPosition = taskNumber - 1;
        if (taskPosition >= tasks.size()) {
            throw new InvalidInputException("invalid task number entered");
        } else {
            Task removedTask = tasks.getTask(taskPosition);
            tasks.removeTask(taskPosition);
            return removedTask;
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task removedTask = deleteTask(taskList);
        storage.update(taskList);
        ui.showDeleteTask(removedTask, taskList);
    }
}
