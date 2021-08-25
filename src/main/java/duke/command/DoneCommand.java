package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskHandler;
import duke.util.Ui;

public class DoneCommand extends Command {
    private final int taskId;

    public DoneCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute(TaskHandler taskHandler, Storage storage, Ui ui) throws DukeException {
        taskHandler.markTaskAsDone(taskId);
        taskHandler.updateData();
    }
}