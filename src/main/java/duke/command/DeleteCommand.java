package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskHandler;
import duke.util.Ui;

public class DeleteCommand extends Command {
    private final int taskId;

    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute(TaskHandler taskHandler, Storage storage, Ui ui) throws DukeException {
        taskHandler.deleteTask(taskId);
        taskHandler.updateData();
    }
}