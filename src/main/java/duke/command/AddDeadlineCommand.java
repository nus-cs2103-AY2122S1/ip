package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class AddDeadlineCommand implements Command {
    String desc;

    public AddDeadlineCommand(String desc) {
        this.desc = desc;
    }

    public void execute(TaskList task, Ui ui, Storage storage) throws DukeException {
        task.addDeadline(desc);
    }

    public boolean isExit() {
        return false;
    }
}
