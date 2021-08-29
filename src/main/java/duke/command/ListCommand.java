package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Command to show the list of tasks.
 */
public class ListCommand extends Command {
    /**
     * Executes the command to show the list of tasks.
     * @param tasks List of tasks.
     * @param ui The Ui object.
     * @param storage The Storage object.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks);
    }

    /**
     * If the command is the exit command.
     * @return False.
     */
    public boolean isExit() {
        return false;
    }
}
