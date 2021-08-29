package duke.commands;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ByeCommand extends Command {

    public ByeCommand(String desc) {
        super(desc);
    }

    @Override
    public boolean isExit() {
        return true;
    };

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayByeMessage();
    }
}
