package duke.command;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * The InvalidCommand class represents the case when the user has entered an invalid command.
 */
public class InvalidCommand extends Command {

    /**
     * Executes the throwing of an error message when user entered an invalid command.
     * @param tasks The list of Task.
     * @param ui The Ui objects that handles input from user and output to user.
     * @param storage The Storage object that handles reading/writing of data.
     * @return String Unused.
     * @throws DukeException Always as user has entered invalid command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String errorMessage = "\t Sorry I do not understand this command \n";
        errorMessage += "\t Please enter a valid command \n";
        throw new DukeException(errorMessage);
    }
}
