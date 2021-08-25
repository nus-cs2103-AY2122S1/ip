package duke.Command;

import duke.Command.Command;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the user command to exit the program.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command. A goodbye message is shown to the user, then the tasks
     * from the TaskList are written and saved into the file.
     *
     * @param taskList A TaskList object that contains an arraylist of Task objects.
     * @param ui A Ui object that deals with interactions with the user.
     * @param storage A Storage object that loads and saves tasks in the file.
     */
    @Override
    public void runCommand(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.showGoodbye();
        storage.save(taskList);
    }

    /**
     * Indicates if the command ends the program after executing.
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
