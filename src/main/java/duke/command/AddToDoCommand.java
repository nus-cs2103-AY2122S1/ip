package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class AddToDoCommand implements Command {
    String desc;

    public AddToDoCommand(String desc) {
        this.desc = desc;
    }

    public void execute(TaskList task, Ui ui, Storage storage) throws DukeException {
        task.addToDo(desc);
    }

    public boolean isExit() {
        return false;
    }
}
