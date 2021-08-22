package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import dukeException.DukeException;

import java.io.IOException;

public class ExitCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        storage.saveTaskList(taskList.getTaskList());
        ui.printMessage(new String[] {"Bye. Hope to see you again soon!"});
    }
}
