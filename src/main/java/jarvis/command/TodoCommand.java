package jarvis.command;

import jarvis.exception.JarvisException;
import jarvis.exception.StorageFileException;
import jarvis.exception.TaskDetailsEmptyException;
import jarvis.storage.Storage;
import jarvis.task.TaskList;
import jarvis.task.Todo;
import jarvis.ui.Ui;

/**
 * Encapsulates the todo task command
 */
public class TodoCommand extends Command {
    private String todoDescription;

    /**
     * Constructor for TodoCommand
     *
     * @param todoDescription The todo task description
     * @throws TaskDetailsEmptyException If the description is empty
     */
    public TodoCommand(String todoDescription) throws TaskDetailsEmptyException {
        if (todoDescription.equals("")) {
            throw new TaskDetailsEmptyException("description");
        }
        this.todoDescription = todoDescription.trim();
    }

    /**
     * Creates the todo task, adds to storage file and shows the ui message to user
     *
     * @param taskList The list in which the tasks are stored
     * @param storage Storage to save or load tasks to hard-disk
     * @param ui Ui to show information to the user
     * @throws StorageFileException If there is an error when storing to storage file
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws StorageFileException {
        Todo newTodo = taskList.addTodo(todoDescription);
        storage.addToStorageFile(newTodo.toStorageFormatString());
        ui.showTaskAddedMessage(newTodo, taskList);
    }
}
