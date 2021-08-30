package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * ExitCommand exits the Duke program.
 *
 * @author Chng Zi Hao
 */
public class ExitCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Exits Duke program.
     *
     * @param taskList   TaskList of Duke.
     * @param ui      The user interface.
     * @param storage Storage for Duke.
     * @return Message to be shown to user.
     * @throws DukeException If we are not able to save the data.txt file.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.printDivider();
        storage.save(taskList.toDataFileInput());
        ui.printGoodbyeMessage();
        ui.printDivider();
        return "Data has been saved! Goodbye!";
    }
}
