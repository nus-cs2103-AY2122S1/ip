package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

/**
 * Represents a command to get help
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    /**
     * Displays help text from given Ui.
     *
     * @param taskList List of tasks.
     * @param ui User interface.
     * @param storage Storage of tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showHelp();
    }
}
