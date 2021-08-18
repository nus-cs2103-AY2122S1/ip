package jarvis.action;

import jarvis.exception.JarvisException;
import jarvis.message.OutputMessage;
import jarvis.output.Output;
import jarvis.task.Task;
import jarvis.task.TaskList;

public class MarkAsDoneAction extends Action {
    private int taskIndex;

    public MarkAsDoneAction(String userInputWithoutActionTrigger) throws JarvisException {
       try {
           this.taskIndex = Integer.parseInt(userInputWithoutActionTrigger.trim()) - 1;
       } catch (NumberFormatException e) {
           throw new JarvisException("Please enter a number!");
       }
    }

    @Override
    public void execute(TaskList taskList) throws JarvisException {
        if (taskList.getTaskListSize() == 0) {
            throw new JarvisException("Please add some tasks first!");
        }
        if (taskIndex < 0 || taskIndex >= taskList.getTaskListSize()) {
            throw new JarvisException("Please enter a valid task number!");
        }
        Task task = taskList.markAsDone(taskIndex);
        Output.showTaskDoneMessage(task);
    }
}
