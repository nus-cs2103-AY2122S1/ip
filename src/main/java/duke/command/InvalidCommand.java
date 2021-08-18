package duke.command;
import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class InvalidCommand extends Command {
    public InvalidCommand() {}

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage store) {
        ui.notifyBadCommand();
    }
}
