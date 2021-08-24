package jarvis.action;

import jarvis.exception.InvalidInputException;
import jarvis.exception.JarvisException;
import jarvis.exception.TaskListEmptyException;
import jarvis.exception.TaskNotFoundException;
import jarvis.output.Output;
import jarvis.storage.Storage;
import jarvis.task.Task;
import jarvis.task.TaskList;

public class MarkAsDoneAction extends Action {
    private int taskIndex;

    public MarkAsDoneAction(String userInputWithoutActionTrigger) throws JarvisException {
       try {
           this.taskIndex = Integer.parseInt(userInputWithoutActionTrigger.trim()) - 1;
       } catch (NumberFormatException e) {
           throw new InvalidInputException("number");
       }
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws JarvisException {
        if (taskList.getTaskListSize() == 0) {
            throw new TaskListEmptyException();
        }
        if (taskIndex < 0 || taskIndex >= taskList.getTaskListSize()) {
            throw new TaskNotFoundException();
        }
        Task task = taskList.markAsDone(taskIndex);
        storage.rewriteStorageFile(taskList);
        Output.showTaskDoneMessage(task);
    }
}
