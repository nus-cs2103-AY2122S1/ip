package duke.commands;

import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * Class that is a subclass of Command class
 * and handles the behaviour of the Command for
 * when an error is encountered
 */
public class ErrorCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printResponse("Invalid input");
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
