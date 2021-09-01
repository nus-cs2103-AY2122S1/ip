package duke.command;
import duke.Ui;
import duke.Storage;
import duke.DukeException;
import duke.task.TaskList;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList ls, Ui ui, Storage storage) throws DukeException {
        ui.goodbye();
        System.exit(0);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
