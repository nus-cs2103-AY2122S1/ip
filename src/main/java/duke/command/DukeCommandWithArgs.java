package duke.command;

import java.util.Map;

import duke.Storage;
import duke.Ui;
import duke.exception.InvalidCommandException;
import duke.task.TaskList;

public class DukeCommandWithArgs {
    private final DukeCommand baseCommand;
    private final String positionalArg;
    private final Map<String, String> namedArgs;

    /**
     * Creates a duke command along with a set of given arguments.
     * @param baseCommand The command that this is based of
     * @param positionalArg The positional argument to apply to the command
     * @param namedArgs The named arguments to apply to the command
     */
    public DukeCommandWithArgs(DukeCommand baseCommand, String positionalArg, Map<String, String> namedArgs) {
        this.baseCommand = baseCommand;
        this.positionalArg = positionalArg;
        this.namedArgs = namedArgs;
    }

    /**
     * Runs the command with the given objects.
     *
     * @param taskList the list containing all the tasks
     * @param ui       the ui of the program
     * @param storage  the storage
     * @throws InvalidCommandException if an error occurs when the command is ran with the given arguments
     */
    public void runWith(TaskList taskList, Ui ui, Storage storage) throws InvalidCommandException {
        baseCommand.apply(taskList, ui, storage, positionalArg, namedArgs);
    }

    public DukeCommand getBaseCommand() {
        return baseCommand;
    }
}
