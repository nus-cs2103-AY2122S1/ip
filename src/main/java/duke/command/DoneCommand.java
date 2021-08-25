package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DoneCommand implements Command {
    String desc;

    public DoneCommand(String desc) {
        this.desc = desc;
    }

    public void execute(TaskList task, Ui ui, Storage storage) throws DukeException {
        task.markDone(desc);
    }

    public boolean isExit() {
        return false;
    }
}
