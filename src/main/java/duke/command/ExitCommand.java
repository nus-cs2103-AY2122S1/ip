package duke.command;

import duke.Ui;
import duke.task.Storage;
import duke.task.TaskList;

/**
 * Represents a Command that exits the program.
 */
public class ExitCommand extends Command {
    /**
     * Executes the program exit command.
     *
     * @param tasks The task list to execute the command on.
     * @param ui The user interface.
     * @param storage The storage for the tasks.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Bye bye! See you again soon!");
    }

    /**
     * Returns true to exit the program.
     * @return true.
     */
    public boolean isExit() {
        return true;
    }
}