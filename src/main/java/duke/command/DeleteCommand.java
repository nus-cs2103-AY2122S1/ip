package duke.command;

import duke.exception.NoSuchTaskException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command{
    private final int taskNumber;

    public DeleteCommand(TaskList taskList, Storage storage, Ui ui, int taskNumber) {
        super(taskList, storage, ui);
        this.taskNumber = taskNumber;
    }

    @Override
    public void runCommand() throws NoSuchTaskException {
        Task deletedTask = taskList.deleteTask(taskNumber);
        ui.taskDeletedMessage(deletedTask, taskList.size());
        storage.save(taskList.getList());
    }
}

