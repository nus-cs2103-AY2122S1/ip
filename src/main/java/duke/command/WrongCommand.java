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

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException {
        Ui.showError(errorMessage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
