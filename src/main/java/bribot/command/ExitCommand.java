package bribot.command;

import bribot.exception.DukeException;
import bribot.storage.Storage;
import bribot.task.TaskList;
import bribot.ui.Ui;

/**
 * Represents an exit command where the program terminates and saves the tasks in the TaskList to a text file.
 */

public class ExitCommand extends Command {

    /**
     * Returns true since it is an exit command.
     * @return true since it is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Executes the program where the storage saves the tasks in the TaskList to the text file and gets the ui
     * to print a goodbye message to the user and close the scanner that takes in input.
     * @param tasks the TaskList where all the tasks are stored.
     * @param ui The ui to print out the user interface and to get input from user.
     * @param storage The storage that interacts with the file to save and load tasks.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.save(tasks.getTasks());
        ui.closeScanner();
        return ui.sayGoodBye();
    }
}
