package duke.command;

import duke.TaskList;
import duke.ui.UserInterface;

public class CommandError extends Command {

    private String errorMessage;
    public CommandError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public void execute(TaskList taskList, UserInterface ui) {
        ui.displayError(errorMessage);
    }
}
