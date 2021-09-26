package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents an execution where the Duke program is terminated.
 */
public class ExitCommand extends Command {
    /**
     * Returns the response after exiting the Duke program.
     *
     * @param tasks   Tasks of the Duke program.
     * @param ui      Ui of the Duke program.
     * @param storage Storage of the Duke program.
     */
    @Override
    public String executeAndGetResponse(TaskList tasks, Ui ui, Storage storage) {
        String response = "Bye. Hope to see you again soon!";
        return response;
    }

    /**
     * Returns true as this command is not the ExitCommand.
     *
     * @return true as this command is not the ExitCommand.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
