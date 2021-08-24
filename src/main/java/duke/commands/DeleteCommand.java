package duke.commands;

import java.io.IOException;

import duke.storage.Storage;
import duke.storage.TaskList;
import duke.tasks.Task;

public class DeleteCommand extends Command {
    private final String taskId;

    public DeleteCommand(String taskId) {
        this.taskId = taskId;
    }

    public void execute(TaskList taskList, Storage storage) {
        try {
            taskList.deleteItem(this.taskId);
            storage.deleteTaskFromData(this.taskId);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void execute(TaskList taskList, Storage storage, Task task) {

    }
}
