package duke.command;

import java.io.IOException;

import duke.Message;
import duke.TaskList;
import duke.exception.DukeException;
import duke.storage.TaskStorage;
import duke.task.Task;
import duke.ui.Ui;

public class MarkTaskAsDoneCommand extends Command {
    private String taskNumberString;

    public MarkTaskAsDoneCommand(String taskNumberString) {
        this.taskNumberString = taskNumberString;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, TaskStorage taskStorage)
            throws DukeException, IOException {
        int taskNumber = retrieveTaskNumber(taskNumberString, taskList);
        Task markedTask = taskList.markTask(taskNumber);
        taskStorage.editTaskFromStorage(taskNumber, markedTask);

        String successMessage = Message.getMarkTaskAsDoneMessage(markedTask);
        ui.setCurrentMessage(successMessage);
    }

}
