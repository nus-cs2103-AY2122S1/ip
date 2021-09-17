package duke.command;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * Abstract class representing commands executed by the user.
 */
public abstract class Command {

    /**
     * Executes the user command.
     * @param tasks The list of Task.
     * @param ui The Ui objects that handles input from user and output to user.
     * @param storage The Storage object that handles reading/writing of data.
     * @return String The output of the application.
     * @throws DukeException On invalid user command.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
