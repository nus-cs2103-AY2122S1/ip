package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class InvalidCommand extends Command {

    public InvalidCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.printErrorMessage("Invalid command!\n"
                + "Try TODO, DEADLINE or EVENT follow by task description.");

    }
}
