package duke.command;

import java.io.IOException;

import duke.Message;
import duke.TaskList;
import duke.exception.DukeException;
import duke.storage.TaskStorage;
import duke.task.Task;
import duke.ui.Ui;

public class DeleteTaskCommand extends Command {
    private String taskNumberString;

    public DeleteTaskCommand(String taskNumberString) {
        this.taskNumberString = taskNumberString;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, TaskStorage taskStorage)
            throws DukeException, IOException {
        int taskNumber = retrieveTaskNumber(taskNumberString, taskList);
        Task removedTask = taskList.deleteTask(taskNumber);
        taskStorage.removeTaskFromStorage(taskNumber);

        String successMessage = Message.getDeleteTaskMessage(removedTask, taskList.getSize());
        ui.setCurrentMessage(successMessage);
    }

}
