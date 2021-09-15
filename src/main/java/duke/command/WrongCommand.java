package duke.command;

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
     *  @param tasks The TaskList which we are modifying.
     * @param ui The Ui we will use for user interaction.
     * @param storage The Storage we will use for storing save data.
     * @return
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return Ui.showError(errorMessage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
