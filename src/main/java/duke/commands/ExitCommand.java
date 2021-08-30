package duke.commands;

import duke.exceptions.UserInputError;
import duke.tasks.TaskList;
import duke.util.Storage;
import duke.util.Ui;

public class ExitCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws UserInputError {
        Storage.writeDatabase(tasks.toArrayList());
        return ui.formatOutput("Bye. Hope to see you again soon!");
    }
}
