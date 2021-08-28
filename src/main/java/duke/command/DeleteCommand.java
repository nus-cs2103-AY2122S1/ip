package duke.command;

import java.io.FileNotFoundException;

import duke.Storage;
import duke.UI;
import duke.task.TaskList;

public class DeleteCommand extends Command {

    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws FileNotFoundException {
        tasks.deleteTask(taskNumber, storage, ui);
    }
}
