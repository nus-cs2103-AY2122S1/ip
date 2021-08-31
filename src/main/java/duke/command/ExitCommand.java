package duke.command;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * The ExitCommand class represents the bye command which exits the program.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command, which exit the application.
     * @param tasks The list of Task.
     * @param ui The Ui objects that handles input from user and output to user.
     * @param storage The Storage object that handles reading/writing of data.
     * @return String The exit message.
     * @throws DukeException Unused.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return ui.displayExitMessage();
    }
}
