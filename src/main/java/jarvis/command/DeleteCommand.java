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
 * Encapsulates the delete command.
 */
public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     * Constructor for DeleteCommand.
     *
     * @param userInputWithoutCommandTrigger User input without the command trigger.
     * @throws InvalidInputException If user input is invalid.
     */
    public DeleteCommand(String userInputWithoutCommandTrigger) throws InvalidInputException {
        try {
            this.taskIndex = Parser.getTaskIndex(userInputWithoutCommandTrigger);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("number");
        }
    }

    /**
     * Deletes the tasks from the list, rewrites the storage file and shows the ui message to user.
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
        Task task = taskList.deleteTask(taskIndex);
        storage.rewriteStorageFile(taskList);
        return ui.getTaskDeletedMessage(task, taskList);
    }
}
