package duke.command;

import duke.exception.ExtraArgumentException;
import duke.util.ExceptionChecker;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * A class that handles exit-program command.
 */
public class ExitCommand extends Command {

    /**
     * Constructs a ExitCommand instance that handles task-listing command.
     *
     * @param command The command input by the user.
     * @throws ExtraArgumentException The exception for handling command with extraneous argument.
     */
    public ExitCommand(String command) throws ExtraArgumentException {
        ExceptionChecker.checkExtraArgument("bye", command);
    }

    /**
     * Returns the response after executing the exit-program command.
     *
     * @param tasks The list that stores all the tasks to be added/deleted.
     * @param ui The ui that deals with interactions with the user.
     * @param storage The storage that deals with loading tasks from the file and saving tasks in the file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "MUDA! MUDA! MUDA! MUDA! ROAD ROLLER DAAAAAAA";
    }

    /**
     * Returns the boolean true since it is a command that exits the program.
     *
     * @return The boolean true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
