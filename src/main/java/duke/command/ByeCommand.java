package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;



public class ByeCommand extends Command {
    public ByeCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }
}
