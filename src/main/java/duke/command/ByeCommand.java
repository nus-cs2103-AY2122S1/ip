package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Ui;

public class ByeCommand extends Command {
    public static final String COMMAND = "Bye!";

    public ByeCommand() {
        this.isExit = true;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printBye();
    }
}
