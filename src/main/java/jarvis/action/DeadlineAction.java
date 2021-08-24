package jarvis.action;

import jarvis.exception.JarvisException;
import jarvis.exception.TaskDetailsEmptyException;
import jarvis.output.Output;
import jarvis.storage.Storage;
import jarvis.task.Deadline;
import jarvis.task.TaskList;

public class DeadlineAction extends Action {
    private String taskDescription;
    private String deadline;

    public DeadlineAction(String userInputWithoutActionTrigger) throws TaskDetailsEmptyException {
        String[] splitStrings = userInputWithoutActionTrigger.split("/by", 2);
        this.taskDescription = splitStrings[0].trim();
        if (taskDescription.equals("")) {
            throw new TaskDetailsEmptyException("description");
        }
        if (splitStrings.length < 2) {
            throw new TaskDetailsEmptyException("deadline");
        }
        this.deadline = splitStrings[1].trim();
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws JarvisException {
        Deadline newDeadlineTask = taskList.addTaskWithDeadline(taskDescription, deadline);
        storage.addToStorageFile(newDeadlineTask.toStorageFormatString());
        Output.showTaskAddedMessage(newDeadlineTask, taskList);
    }
}
