package duke.commands;

import java.io.IOException;

import duke.storage.Storage;
import duke.storage.TaskList;

public class DeleteCommand extends Command {
    private final String taskId;

    public DeleteCommand(String taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        try {
            taskList.deleteItem(this.taskId);
            storage.deleteTaskFromData(this.taskId);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public boolean getIsExit() {
        return false;
    };
}
