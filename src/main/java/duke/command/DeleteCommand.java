package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.task.Task;
import duke.ui.UiPane;

public class DeleteCommand extends Command {
    private final int serialNo;

    public DeleteCommand(int serialNo) {
        this.serialNo = serialNo;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, UiPane uiPane) throws DukeException {
        Task task = taskList.delete(serialNo);
        storage.write(taskList.getTasks());
        uiPane.showTaskList(taskList.getTasks());
        uiPane.showMessage(
                String.format(
                        "You have deleted the task: %s. You now have %d tasks.",
                        task.getDescription(), taskList.getTaskCount()
                )
        );
    }
}
