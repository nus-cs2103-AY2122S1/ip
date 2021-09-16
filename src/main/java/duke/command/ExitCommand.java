package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A class which encapsulates the command of
 * exiting the program.
 */
public class ExitCommand extends Command {

    /**
     * A constructor which calls the parent constructor
     * with the command.
     *
     * @param command The command inputted by the user.
     */
    public ExitCommand(String command) {

        super(command);
    }

    /**
     * Returns true as this is an exit command.
     *
     * @return The boolean value representing true.
     */

    @Override
    public boolean isBye() {

        return true;
    }

    /**
     * Executes the exit command to end the program.
     *
     * @param tasks   The list of tasks stored so far.
     * @param ui      The Ui to deal with interactions with user.
     * @param storage The storage which saves and edits file content.
     * @return The string indicating the command has been executed.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {

        return ui.sayBye();
    }
}
