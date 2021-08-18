package jarvis.action;

import jarvis.exception.InvalidInputException;
import jarvis.exception.JarvisException;
import jarvis.exception.TaskListEmptyException;
import jarvis.exception.TaskNotFoundException;
import jarvis.output.Output;
import jarvis.task.Task;
import jarvis.task.TaskList;

public class DeleteAction extends Action {
    private int taskIndex;

    public DeleteAction(String userInputWithoutActionTrigger) throws InvalidInputException {
        try {
            this.taskIndex = Integer.parseInt(userInputWithoutActionTrigger.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidInputException("number");
        }
     }

    @Override
    public void execute(TaskList taskList) throws JarvisException {
        if (taskList.getTaskListSize() == 0) {
            throw new TaskListEmptyException();
        }
        if (taskIndex < 0 || taskIndex >= taskList.getTaskListSize()) {
            throw new TaskNotFoundException();
        }
        Task task = taskList.deleteTask(taskIndex);
        Output.showTaskDeletedMessage(task, taskList);
    }
}
