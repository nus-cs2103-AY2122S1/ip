package jarvis.command;

import jarvis.exception.InvalidInputException;
import jarvis.exception.JarvisException;
import jarvis.exception.TaskListEmptyException;
import jarvis.exception.TaskNotFoundException;
import jarvis.parser.Parser;
import jarvis.ui.Ui;
import jarvis.storage.Storage;
import jarvis.task.Task;
import jarvis.task.TaskList;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(String userInputWithoutCommandTrigger) throws InvalidInputException {
        try {
            this.taskIndex = Parser.getTaskIndex(userInputWithoutCommandTrigger);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("number");
        }
     }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws JarvisException {
        if (taskList.getTaskListSize() == 0) {
            throw new TaskListEmptyException();
        }
        if (taskIndex < 0 || taskIndex >= taskList.getTaskListSize()) {
            throw new TaskNotFoundException();
        }
        Task task = taskList.deleteTask(taskIndex);
        storage.rewriteStorageFile(taskList);
        ui.showTaskDeletedMessage(task, taskList);
    }
}
