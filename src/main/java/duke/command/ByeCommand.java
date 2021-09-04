package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The bye command, that terminates the program.
 */
public class ByeCommand extends Command {

    /**
     * Constructs the bye command.
     */
    public ByeCommand() {
        super("bye");
    }

    /**
     * Executes the main logic of the bye command.
     *
     * @param tasks   The user's list of tasks.
     * @param ui      The ui interacting with the user.
     * @param storage The location where the list of tasks is stored.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }

}
