package duke.command;

import static duke.util.Ui.EXIT_MESSAGE;

import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Represents the bye command.
 */
public class ByeCommand extends Command {
    protected static final String COMMAND = "bye";

    protected ByeCommand() {
    }

    /**
     * Executes the command.
     *
     * @param taskList The taskList keeping track of the tasks.
     * @param ui The Ui used for the user interface.
     * @param storage The storage object taking care of writing and reading the text file.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return EXIT_MESSAGE;
    }
}
