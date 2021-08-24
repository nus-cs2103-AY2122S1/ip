package jarvis.action;

import jarvis.exception.StorageFileException;
import jarvis.exception.TaskDetailsEmptyException;
import jarvis.output.Output;
import jarvis.storage.Storage;
import jarvis.task.TaskList;
import jarvis.task.Todo;

public class TodoAction extends Action {
    private String todoDescription;

    public TodoAction(String todoDescription) throws TaskDetailsEmptyException {
        if (todoDescription.equals("")) {
            throw new TaskDetailsEmptyException("description");
        }
        this.todoDescription = todoDescription.trim();
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws StorageFileException {
        Todo newTodo = taskList.addTodo(todoDescription);
        storage.addToStorageFile(newTodo.toStorageFormatString());
        Output.showTaskAddedMessage(newTodo, taskList);
    }
}
