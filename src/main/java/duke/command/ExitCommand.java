package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A class that represents a command that exit Duke.
 */
public class ExitCommand extends Command {
    /**
     * Constructs a {@code ExitCommand} object.
     */
    public ExitCommand() {
        super("");
    }

    /**
     * Executes the command.
     *
     * @param taskList The task list that may be modified or referenced by the command.
     * @param storage  The storage that may be modified of referenced by the command.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return Ui.farewell();
    }

    /**
     * Indicates if the command is an exit command.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
