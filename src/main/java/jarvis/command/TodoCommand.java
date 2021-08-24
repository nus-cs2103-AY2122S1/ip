package jarvis.command;

import jarvis.exception.StorageFileException;
import jarvis.exception.TaskDetailsEmptyException;
import jarvis.storage.Storage;
import jarvis.task.TaskList;
import jarvis.task.Todo;
import jarvis.ui.Ui;

public class TodoCommand extends Command {
    private String todoDescription;

    public TodoCommand(String todoDescription) throws TaskDetailsEmptyException {
        if (todoDescription.equals("")) {
            throw new TaskDetailsEmptyException("description");
        }
        this.todoDescription = todoDescription.trim();
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws StorageFileException {
        Todo newTodo = taskList.addTodo(todoDescription);
        storage.addToStorageFile(newTodo.toStorageFormatString());
        ui.showTaskAddedMessage(newTodo, taskList);
    }
}
