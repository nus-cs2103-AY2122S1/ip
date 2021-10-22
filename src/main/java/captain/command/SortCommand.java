package captain.command;

import captain.DukeException;
import captain.Storage;
import captain.Ui;
import captain.task.TaskList;

public class SortCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return "SortCommand is in progress";
    }
}
