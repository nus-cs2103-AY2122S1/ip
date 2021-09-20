package duke.command;

import duke.gui.Ui;
import duke.task.TaskList;
import duke.util.Storage;


/**
 * Command to get help on the available commands.
 */
public class HelpCommand extends Command {
    /**
     * Executes the command to ask for help.
     * @param tasks The list of tasks.
     * @param ui The Ui object.
     * @param storage The Storage object.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showHelp();
    }

    /**
     * If the command is the exit command.
     * @return False.
     */
    public boolean isExit() {
        return false;
    }
}
