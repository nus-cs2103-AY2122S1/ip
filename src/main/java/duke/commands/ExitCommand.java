package duke.commands;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.DukeException;
import duke.storage.Storage;

public class ExitCommand  extends Command {
    public ExitCommand() {
        this.isExit = true;
    }
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return "Bye. Hope to see you again soon!";
    }
}
