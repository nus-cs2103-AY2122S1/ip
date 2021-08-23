package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class ExitCommand extends Command {
    /**
     * An override method to check whether the Command is an ExitCommand.
     *
     * @return Always return true indicating this is an ExitCommand.
     */
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
