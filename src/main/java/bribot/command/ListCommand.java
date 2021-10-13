package bribot.command;

import bribot.exception.DukeException;
import bribot.storage.Storage;
import bribot.task.Task;
import bribot.task.TaskList;
import bribot.ui.Ui;

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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        StringBuilder response = new StringBuilder();
        response.append(ui.printStartList());
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            response.append(ui.printTask(task, i + 1));
        }
        return response.toString();
    }
}
