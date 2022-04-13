package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class QuitCommand implements Command {

    public String execute(Ui ui, TaskList taskList, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }

    public boolean isQuit() {
        return true;
    }
}
