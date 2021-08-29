package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;

public class ByeCommand extends Command {

    public ByeCommand(String userCommand, String userArgument) {
        super(userCommand, userArgument);
    }

    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.goodBye();
    }

    public boolean isExit() {
        return true;
    }

}
