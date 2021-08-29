package duke.command;

import java.io.FileNotFoundException;

import duke.ResponseLogic;
import duke.Storage;
import duke.task.TaskList;

public class DeleteCommand extends Command {

    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(TaskList tasks, ResponseLogic responseLogic, Storage storage) throws FileNotFoundException {
        return tasks.deleteTask(taskNumber, storage, responseLogic);
    }
}
