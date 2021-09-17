package duke.commands;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;


public class ExitCommand extends Command {
    public ExitCommand() {
        this.setExitStatus();
    }
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return "Bye. Hope to see you again soon!";
    }
}
