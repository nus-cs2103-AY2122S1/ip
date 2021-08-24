package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand implements Command {
    String desc;
    public DeleteCommand(String desc) {
        this.desc = desc;
    }
    public void execute(TaskList task, Ui ui, Storage storage) throws DukeException {
        task.delete(desc);
    }

    public boolean isExit() {
        return false;
    }
}