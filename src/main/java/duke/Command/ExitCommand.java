package duke.Command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to exit program
 */
public class ExitCommand extends Command {

    /**
     * Exits program
     * @param tasks Current TaskList
     * @param ui Ui object of bot
     * @param storage Storage object of bot
     * @return Confirmation message
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
