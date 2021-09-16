package duke.commands;

import duke.TaskArrayList;
import duke.exceptions.DukeException;
import duke.exceptions.DukeExitException;

/**
 * Command to execute when user types "bye".
 */
public class CommandBye extends Command {
    public static final String HELP_COMMAND = "bye";
    public static final String HELP_DESCRIPTION = "Gracefully terminate the program";
    public static final String HELP_USAGE =
            "Usage: bye\n"
            + HELP_DESCRIPTION;

    private static final int REQUIRED_ARG_COUNT = 1;


    public CommandBye(String[] cmdArgsArr, TaskArrayList taskList) {
        super(cmdArgsArr, taskList);
    }

    @Override
    public String run() throws DukeException {
        if (hasWrongArgumentCount(REQUIRED_ARG_COUNT)) {
            throw new DukeException(HELP_USAGE);
        }

        throw new DukeExitException("BYE");
    }
}
