package jarvis.command;

import jarvis.exception.JarvisException;
import jarvis.exception.TaskDetailsEmptyException;
import jarvis.parser.Parser;
import jarvis.ui.Ui;
import jarvis.storage.Storage;
import jarvis.task.Deadline;
import jarvis.task.TaskList;

public class DeadlineCommand extends Command {
    private String taskDescription;
    private String deadline;

    public DeadlineCommand(String userInputWithoutCommandTrigger) throws TaskDetailsEmptyException {
        String[] splitStrings = Parser.getDeadlineInfoArray(userInputWithoutCommandTrigger);
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
    public void execute(TaskList taskList, Storage storage, Ui ui) throws JarvisException {
        Deadline newDeadlineTask = taskList.addTaskWithDeadline(taskDescription, deadline);
        storage.addToStorageFile(newDeadlineTask.toStorageFormatString());
        ui.showTaskAddedMessage(newDeadlineTask, taskList);
    }
}
