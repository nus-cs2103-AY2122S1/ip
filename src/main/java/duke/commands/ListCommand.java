package duke.commands;

import duke.exceptions.UserInputError;
import duke.tasks.TaskList;
import duke.util.Ui;

public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui) throws UserInputError {
        return ui.formatOutput(tasks.toString());
    }
}
