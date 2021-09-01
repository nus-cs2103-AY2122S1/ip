package duke.command;

import duke.exception.InvalidInputException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class ExitCommand extends Command {

    /**
     * Executes the specified command.
     *
     * @param tasks The TaskList which we are modifying.
     * @param ui The Ui we will use for user interaction.
     * @param storage The Storage we will use for storing save data.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.write(tasks);
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
