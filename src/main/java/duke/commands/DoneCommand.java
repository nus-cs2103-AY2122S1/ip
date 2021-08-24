package duke.commands;

import java.io.IOException;

import duke.storage.Storage;
import duke.storage.TaskList;

public class DoneCommand extends Command {
    private final String taskId;

    public DoneCommand(String taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        try {
            taskList.doneItem(this.taskId);
            storage.updateDone(this.taskId);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public boolean getIsExit() {
        return false;
    }
}