package jarvis.command;

import jarvis.exception.JarvisException;
import jarvis.exception.TaskDetailsEmptyException;
import jarvis.parser.Parser;
import jarvis.storage.Storage;
import jarvis.task.Deadline;
import jarvis.task.TaskList;
import jarvis.ui.Ui;

/**
 * Encapsulates the deadline task command
 */
public class DeadlineCommand extends Command {
    private String taskDescription;
    private String deadline;

    /**
     * Constructor for DeadlineCommand
     *
     * @param userInputWithoutCommandTrigger User input without the command trigger
     * @throws TaskDetailsEmptyException If any of the required details are empty
     */
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

    /**
     * Creates the deadline task, adds to storage file and shows the ui message to user
     *
     * @param taskList The list in which the tasks are stored
     * @param storage Storage to save or load tasks to hard-disk
     * @param ui Ui to show information to the user
     * @throws JarvisException If there is an error
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws JarvisException {
        Deadline newDeadlineTask = taskList.addTaskWithDeadline(taskDescription, deadline);
        storage.addToStorageFile(newDeadlineTask.toStorageFormatString());
        ui.showTaskAddedMessage(newDeadlineTask, taskList);
    }
}
