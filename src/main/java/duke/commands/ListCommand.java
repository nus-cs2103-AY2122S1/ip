package duke.commands;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;


public class ListCommand extends Command {
    public ListCommand() {}

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return tasks.toString();
    }
}
