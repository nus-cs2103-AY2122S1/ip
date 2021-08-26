package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class ExitCommand extends Command {

    public ExitCommand() {}

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.print("Goodbye! Have a nice day!");
    }

    public Boolean isExit() {
        return true;
    }

}
