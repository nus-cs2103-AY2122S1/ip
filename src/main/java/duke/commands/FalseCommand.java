package duke.commands;

import duke.exceptions.UserInputError;
import duke.tasks.TaskList;
import duke.util.Storage;
import duke.util.Ui;

public class FalseCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws UserInputError {
        return ui.formatOutput("OOPS! Invalid command input! Please try again.");
    }
}
