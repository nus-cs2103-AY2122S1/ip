package duke.command;

import duke.exception.InvalidInputException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class WrongCommand extends Command {

    private String errorMessage;

    public WrongCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Executes the specified command.
     *
     * @param tasks The TaskList which we are modifying.
     * @param ui The Ui we will use for user interaction.
     * @param storage The Storage we will use for storing save data.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.showError(errorMessage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
