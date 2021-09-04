package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * The ExitCommand is given when the user wants to exit Duke.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";
    public static final String EXIT_MSG = "Bye. Hope to see you again soon!";

    public ExitCommand() {

    }

    /**
     * Prints the Exit Message.
     *
     * @param tasks the given TaskList.
     * @param storage the given Storage.
     * @return the string for the Ui to print.
     * @throws DukeException shouldn't happen.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return EXIT_MSG;
    }

    /**
     * Returns true since this is the exit Command.
     *
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
