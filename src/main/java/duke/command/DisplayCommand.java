package duke.command;

import duke.Ui;
import duke.task.Storage;
import duke.task.TaskList;

/**
 * Represents a Command that displays tasks in the TaskList.
 */
public class DisplayCommand extends Command {

    /**
     * Executes the display list command.
     *
     * @param tasks The task list to execute the command on.
     * @param ui The user interface.
     * @param storage The storage for the tasks.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.displayList(ui);
    }

    /**
     * Returns false to continue the program.
     *
     * @return false.
     */
    public boolean isExit() {
        return false;
    }
}