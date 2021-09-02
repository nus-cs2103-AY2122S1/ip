package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {
    /**
     * Creates a command that exits the program.
     *
     * @param arguments Command arguments.
     */
    public ByeCommand(String arguments) throws Exception {
        if (arguments.length() > 0) {
            throw new Exception("Command `bye` does not accept arguments");
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
