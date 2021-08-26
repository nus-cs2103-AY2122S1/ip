package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.task.Task;

public class DeleteCommand implements Command {

    private int startOfString;

    public DeleteCommand(int startOfString) {
        this.startOfString = startOfString;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task taskToDelete = taskList.deleteTask(startOfString);
        storage.deleteFromFile(taskToDelete);
        ui.markAsDeleted(taskToDelete);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DeleteCommand)) {
            return false;
        }
        DeleteCommand other = (DeleteCommand) obj;
        return startOfString == other.startOfString;
    }
}
