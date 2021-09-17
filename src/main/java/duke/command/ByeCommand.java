package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.InvalidCommandException;
import duke.ui.Ui;

public class ByeCommand extends Command {
    /**
     * Creates a command that exits the program.
     *
     * @param arguments Command arguments.
     */
    public ByeCommand(String arguments) throws InvalidCommandException {
        if (arguments.length() > 0) {
            throw new InvalidCommandException("Command `bye` does not accept arguments");
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // No-op.
    }

    @Override
    public boolean shouldExit() {
        return true;
    }
}
