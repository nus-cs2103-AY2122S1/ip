package duke.commands;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.DukeException;
import duke.storage.Storage;

public class ListCommand extends Command {
    public ListCommand() {}

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return tasks.toString();
    }
    
}
