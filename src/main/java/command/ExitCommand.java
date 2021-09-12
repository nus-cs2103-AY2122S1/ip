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

    /**
     * A method to execute the ExitCommand. When the method is executed,
     * Duke will store the current taskList to the hard disc and quit.
     *
     * @param taskList The given Duke TaskList.
     * @param ui The given Duke Ui.
     * @param storage The given Duke Storage.
     * @throws DukeException Exception thrown when execute the ExitCommand.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        storage.saveTaskList(taskList.getTaskList());
        return ui.generateDukeResponse("Bye. Hope to see you again soon!");
    }
}
