package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class AddEventCommand implements Command {
    String desc;
    public AddEventCommand(String desc) {
        this.desc = desc;
    }
    public void execute(TaskList task, Ui ui, Storage storage) throws DukeException {
        task.addEvent(desc);
    }

    public boolean isExit() {
        return false;
    }
}
