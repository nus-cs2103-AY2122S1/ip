package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
/**
 * An abstract class to represent the command
 * of the user.
 */
public abstract class Command {

    /** The string to contain the command. */
    private String command;

    /**
     * A public constructor to initialise the command
     * to the given command.
     *
     * @param command A string representing input from user.
     */
    public Command(String command) {
        this.command = command;

    }

    /**
     * Returns false as user is not exiting the
     * program yet.
     *
     * @return The boolean value representing false.
     */
    public boolean isBye() {
        return false;
    }

    /**
     * An abstract method for execution of commands.
     *
     * @param tasks The list of tasks stored so far.
     * @param ui The Ui to deal with interactions with user.
     * @param storage The storage which saves and edits file content.
     * @throws DukeException
     */
    public abstract String execute(TaskList tasks,
            Ui ui, Storage storage) throws DukeException;
}
