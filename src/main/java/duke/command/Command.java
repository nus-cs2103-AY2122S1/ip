package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Commands that the bot can handle.
 */
public abstract class Command {

    /**
     * The command keyword associated with the command
     **/
    private String command;

    /**
     * Constructs the command with the user input.
     *
     * @param command The keyword representing the command.
     */
    public Command(String command) {
        this.command = command;
    }

    /**
     * Executes the main logic of the command
     *
     * @param tasks   The user's list of tasks.
     * @param ui      The ui interacting with the user.
     * @param storage The location where the list of tasks is stored.
     * @throws DukeException
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks whether command terminate the program.
     *
     * @return true if command is the bye command; else false.
     */
    public abstract boolean isExit();

    /**
     * Returns the given command keyword.
     *
     * @return The string representing the command keyword.
     */
    public String getCommand() {
        return command;
    }

}
