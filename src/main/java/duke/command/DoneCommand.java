package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.task.Task;

public class DoneCommand implements Command {

    private int startOfString;

    public DoneCommand(int startOfString) {
        this.startOfString = startOfString;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task taskToChange = taskList.retrieveTask(startOfString);
        storage.changeDone(taskToChange);
        ui.markAsDone(taskToChange);
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
        if (!(obj instanceof DoneCommand)) {
            return false;
        }
        DoneCommand other = (DoneCommand) obj;
        return startOfString == other.startOfString;
    }
}
