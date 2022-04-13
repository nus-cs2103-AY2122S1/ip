package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the user command to output the app usage menu.
 */
public class HelpCommand extends Command {

    /**
     * Executes the help command. Returns the app usage menu.
     *
     * @param taskList A TaskList object that contains an arraylist of Task objects.
     * @param ui A Ui object that deals with interactions with the user.
     * @param storage A Storage object that loads and saves tasks in the file.
     */
    @Override
    public String runCommand(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        return ui.showHelp();
    }

    /**
     * Indicates if the command ends the program after executing.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
