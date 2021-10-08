package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.task.Task;
import duke.ui.UiPane;

public class DoneCommand extends Command {
    private final int serialNo;

    public DoneCommand(int serialNo) {
        this.serialNo = serialNo;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, UiPane uiPane) throws DukeException {
        Task task = taskList.markDone(serialNo);
        storage.write(taskList.getTasks());
        uiPane.showTaskList(taskList.getTasks());
        uiPane.showMessage(
                String.format(
                        "You have marked the following task as done: %s.",
                        task.getDescription()
                )
        );
    }
}
