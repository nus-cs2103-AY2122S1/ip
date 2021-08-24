package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class Command {
    public void execute(TaskList tl, Storage st, Ui ui) {

    }

    public boolean isArgumentValid() {
        return true;
    }

    public boolean isExit() {
        return false;
    }
}
