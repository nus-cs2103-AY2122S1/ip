package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents the ListCommand in the Duke program.
 */
public class ListCommand extends Command {
    /**
     * Constructs a ListCommand.
     */
    public ListCommand() {
    }

    /**
     * Defines the execution of the ListCommand where all created tasks are listed.
     *
     * @param tasks   Tasks of the Duke program.
     * @param ui      Ui of the Duke program.
     * @param storage Storage of the Duke program.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String response;
        if (tasks.getSize() == 0) {
            response = "You do not have any tasks.";
        } else {
            response = "Here are the tasks in your list:\n" + tasks;
        }
        ui.showResponse(response);
    }

    /**
     * Returns false as this command is not the ExitCommand.
     *
     * @return false as this command is not the ExitCommand.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
