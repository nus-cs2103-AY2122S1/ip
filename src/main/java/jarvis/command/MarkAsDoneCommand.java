package jarvis.command;

import jarvis.exception.InvalidInputException;
import jarvis.exception.JarvisException;
import jarvis.exception.TaskListEmptyException;
import jarvis.exception.TaskNotFoundException;
import jarvis.message.OutputMessage;
import jarvis.parser.Parser;
import jarvis.storage.Storage;
import jarvis.task.Task;
import jarvis.task.TaskList;
import jarvis.ui.Ui;

/**
 * Encapsulates the mark as done command.
 */
public class MarkAsDoneCommand extends Command {
    private int taskIndex;

    /**
     * Constructor for MarkAsDoneCommand.
     *
     * @param userInputWithoutCommandTrigger User input without the command trigger.
     * @throws JarvisException If the user input is invalid.
     */
    public MarkAsDoneCommand(String userInputWithoutCommandTrigger) throws JarvisException {
        try {
            this.taskIndex = Parser.getTaskIndex(userInputWithoutCommandTrigger);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("number");
        }
    }

    /**
     * Marks the task as done, rewrites the storage file and shows the ui message to user.
     *
     * @param taskList The list in which the tasks are stored.
     * @param storage Storage to save or load tasks to hard-disk.
     * @param ui Ui to show information to the user.
     * @return A OutputMessage that needs to be shown to the user after execution.
     * @throws JarvisException If there is an error.
     */
    @Override
    public OutputMessage execute(TaskList taskList, Storage storage, Ui ui) throws JarvisException {
        if (taskList.getTaskListSize() == 0) {
            throw new TaskListEmptyException();
        }
        if (taskIndex < 0 || taskIndex >= taskList.getTaskListSize()) {
            throw new TaskNotFoundException();
        }
        Task task = taskList.markAsDone(taskIndex);
        storage.rewriteStorageFile(taskList);
        return ui.getTaskDoneMessage(task);
    }
}
