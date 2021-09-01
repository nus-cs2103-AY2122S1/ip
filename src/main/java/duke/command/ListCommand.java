package duke.command;
import duke.Ui;
import duke.Storage;
import duke.DukeException;
import duke.task.TaskList;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList ls, Ui ui, Storage storage) throws DukeException {
        ui.printTaskList(ls);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
