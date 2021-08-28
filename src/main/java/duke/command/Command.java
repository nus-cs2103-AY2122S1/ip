package duke.command;

import java.io.FileNotFoundException;

import duke.Storage;
import duke.UI;
import duke.task.TaskList;

public abstract class Command {

    /**
     * Executes the current command.
     *
     * @param tasks The TaskList object containing the list of tasks.
     * @param ui The UI object of the current Duke object.
     * @param storage The Storage object of the current Duke object.
     * @throws FileNotFoundException If the file containing the tasks is not found.
     */
    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws FileNotFoundException;

    /**
     * Returns true if the program should exit.
     *
     * @return Exit boolean of the Duke program.
     */
    public boolean isExit() {
        return false;
    }
}
