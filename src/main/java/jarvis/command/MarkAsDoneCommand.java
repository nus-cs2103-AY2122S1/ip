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

public class MarkAsDoneCommand extends Command {
    private int taskIndex;

    public MarkAsDoneCommand(String userInputWithoutCommandTrigger) throws JarvisException {
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
        Task task = taskList.markAsDone(taskIndex);
        storage.rewriteStorageFile(taskList);
        ui.showTaskDoneMessage(task);
    }
}
