package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.ui.Ui;

/**
 * A class that models a command to say bye and exit the program.
 */
public class CommandBye extends Command {

    /**
     * Call the Ui object to say bye to user and instructs to exit the main loop.
     *
     * @param tasks   The taskList to add new task in.
     * @param ui      The Ui object to print messages after action.
     * @param storage The Storage object that auto-saves after modification.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        this.isExit = true;
        return "Bye, hope to see you soon!";

    }
}
