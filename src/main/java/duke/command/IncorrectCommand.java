package duke.command;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class IncorrectCommand extends Command {

    public IncorrectCommand() {
    }

    @Override
    public boolean isExist() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
    }
}

