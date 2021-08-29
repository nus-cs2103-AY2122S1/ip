package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Command to exit the program.
 */
public class ExitCommand extends Command {

    /**
     * Executes the command to exit the program.
     * @param tasks The list of tasks.
     * @param ui The Ui object.
     * @param storage The Storage object.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFarewell();
        storage.saveFile(tasks.saveList());
    }

    /**
     * If the command is the exit command.
     * @return True.
     */
    public boolean isExit() {
        return true;
    }
}
