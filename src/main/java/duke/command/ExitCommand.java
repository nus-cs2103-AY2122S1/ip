package duke.command;

import duke.gui.Ui;
import duke.task.TaskList;
import duke.util.DukeException;
import duke.util.Storage;

/**
 * Command to exit the program.
 */
public class ExitCommand extends Command {

    /**
     * Executes the command to exit the program.
     * @param tasks The list of tasks.
     * @param ui The Ui object.
     * @param storage The Storage object.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Storage.saveFile(tasks.saveList());
        return ui.showFarewell();
    }

    /**
     * If the command is the exit command.
     * @return True.
     */
    public boolean isExit() {
        return true;
    }
}
