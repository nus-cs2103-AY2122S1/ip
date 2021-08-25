package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that when executed, lists the tasks in the TaskList for the user to see.
 */

public class ListCommand extends Command {
    /**
     * Returns false since it is not an exit command.
     * @return false since it is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command where the ui prints out each task in the TaskList for the user to see.
     * @param tasks the TaskList where all the tasks are stored.
     * @param ui The ui to print out the user interface and to get input from user.
     * @param storage The storage that interacts with the file to save and load tasks.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printStartList();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            ui.printTask(task, i + 1);
        }
        ui.printLine();
    }
}
