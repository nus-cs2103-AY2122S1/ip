package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class IncorrectCommand extends Command {
    String errorMessage;

    public IncorrectCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError(errorMessage);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return "[error]";
    }
}
