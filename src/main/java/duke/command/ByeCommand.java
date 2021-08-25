package duke.command;

import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;


public class ByeCommand extends Command {
    public ByeCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.showBye();
    }
}
